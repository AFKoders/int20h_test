package com.afkoders.musicakinator.presentation.interation_with_akinator

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.prefs.HistoryPrefs
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.data.repository.DeezerRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class InteractionViewModel @Inject constructor(
    private val history: HistoryPrefs,
    private val auddRepository: AuddRepository,
    private val deezerRepository: DeezerRepository,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {

    val dateFormat = SimpleDateFormat("MMM d, hh:mm a" /* Apr 29, 4:11 PM */, Locale.US)

    var attemptsCount: Int = MAX_ATTEMPTS_COUNT
        private set

    var maxAttempts: Int = MAX_ATTEMPTS_COUNT
        private set

    private val trackData = mutableListOf<Intermix>()

    private var routeOnNextInteraction: Interaction = Interaction.Retry(attemptsCount)

    private fun updateAttemptsCount(shouldContinueGuessing: Boolean) {
        attemptsCount = if (shouldContinueGuessing) attemptsCount.minus(1) else maxAttempts
    }

    private fun updateRoute(shouldContinueGuessing: Boolean) {
        routeOnNextInteraction = if (shouldContinueGuessing) {
            Interaction.Retry(attemptsCount)
        } else {
            Interaction.Failure
        }
    }

    private fun normalizedId() = maxAttempts - attemptsCount

    fun update() {
        val shouldContinueGuessing = attemptsCount > 0

        updateAttemptsCount(shouldContinueGuessing)
        updateRoute(shouldContinueGuessing)
    }

    fun route(isAkinatorMadeRightGuess: Boolean): Interaction {
        return if (isAkinatorMadeRightGuess) {
            val result = getTrackByAttempt()
            saveGuessedTrack(result)
            Interaction.Success(result)
        } else {
            routeOnNextInteraction
        }
    }

    private fun saveGuessedTrack(result: Intermix) {
        history.addToHistory(result.toHistory(dateFormat.format(Date())))
    }

    fun getTrackByAttempt() = trackData[normalizedId()]

    fun reset() {
        updateAttemptsCount(false)
        updateRoute(true)
        trackData.clear()
        maxAttempts = MAX_ATTEMPTS_COUNT
        attemptsCount = MAX_ATTEMPTS_COUNT
    }

    fun putItems(newTrackData: List<Intermix>) {
        check(newTrackData.size <= MAX_ATTEMPTS_COUNT) { "Size of tracks list should be less or equal to $MAX_ATTEMPTS_COUNT" }

        trackData.addAll(newTrackData)

        maxAttempts = newTrackData.size
        attemptsCount = newTrackData.size
    }

    fun searchSongs(lyrics: String): Single<List<Intermix>> =
        auddRepository
            .findSongsByLyrics(lyrics)
            .flatMap {
                val deezerObservablesList: List<Single<Pair<Song, DeezerSong>>> = it.take(5)
                    .map { song -> mapToSongAndDeezerSong(song) }

                Single.zip(deezerObservablesList) { arr: Array<Any> ->
                    arr.toList()
                        .map { it as Pair<Song, DeezerSong> }
                        .filter { !it.first.songId.isNullOrBlank() }
                        .map { (song, deezerSong) -> createIntermixModel(song, deezerSong) }
                }.map { if (it.isEmpty()) return@map listOf(EmptyIntermix) else it }
            }
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)

    private fun createIntermixModel(
        song: Song,
        deezerSong: DeezerSong
    ): Intermix {
        return Intermix(
            deezerSong.link ?: "",
            song.songId?.toInt() ?: 0,
            deezerSong.title ?: "",
            song.lyrics ?: "",
            deezerSong.album?.picture ?: "",
            song.artist ?: ""
        )
    }

    private fun mapToSongAndDeezerSong(song: Song): Single<Pair<Song, DeezerSong>> {
        val songName = "${song.title} ${song.artist}"
        val defaultValue = Song() to DeezerSong()

        return if (!songName.isBlank()) {
            deezerRepository.findSongByTitle(songName)
                .map { song to it }
                .onErrorReturnItem(defaultValue)
        } else {
            Single.just(defaultValue)
        }
    }

    fun isTracksCompatible() = !trackData.contains(EmptyIntermix)

    companion object {
        const val MAX_ATTEMPTS_COUNT = 5
    }
}
package com.afkoders.musicakinator.presentation.interation_with_akinator

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.data.repository.DeezerRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class InteractionViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    private val deezerRepository: DeezerRepository,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {

    var attemptsCount: Int = MAX_ATTEMPTS_COUNT
        private set

    var maxAttempts: Int = MAX_ATTEMPTS_COUNT
        private set

    private val trackData = mutableListOf<Intermix>()

    private var routeOnNextInteraction: Interaction = Interaction.Retry(normalizedId())

    private fun updateAttemptsCount(shouldContinueGuessing: Boolean) {
        attemptsCount = if (shouldContinueGuessing) attemptsCount.minus(1) else MAX_ATTEMPTS_COUNT
    }

    private fun updateRoute(shouldContinueGuessing: Boolean) {
        routeOnNextInteraction = if (shouldContinueGuessing) {
            Interaction.Retry(normalizedId())
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
            Interaction.Success(getTrackByAttempt())
        } else {
            routeOnNextInteraction
        }
    }

    fun getTrackByAttempt() = trackData[normalizedId()]

    fun reset() {
        updateAttemptsCount(false)
        updateRoute(true)
        trackData.clear()
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
                }
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

    companion object {
        const val MAX_ATTEMPTS_COUNT = 5
    }
}
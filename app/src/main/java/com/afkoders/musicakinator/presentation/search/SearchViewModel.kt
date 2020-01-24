package com.afkoders.musicakinator.presentation.search

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.data.repository.DeezerRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import com.afkoders.musicakinator.presentation.interation_with_akinator.Intermix
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    private val deezerRepository: DeezerRepository,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {
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
        val songName = song.fullTitle ?: song.title
        val defaultValue = Song() to DeezerSong()

        return if (!songName.isNullOrBlank()) {
            deezerRepository.findSongByTitle(songName)
                .map { song to it }
                .onErrorReturnItem(defaultValue)
        } else {
            Single.just(defaultValue)
        }
    }
}
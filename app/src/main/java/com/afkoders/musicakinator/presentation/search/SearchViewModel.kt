package com.afkoders.musicakinator.presentation.search

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.data.repository.DeezerRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class SearchViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    private val deezerRepository: DeezerRepository,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {
    fun searchSongs(lyrics: String): Single<List<DeezerSong>> =
        auddRepository
            .findSongsByLyrics(lyrics)
            .flatMap {
                val deezerObservablesList: List<Single<DeezerSong>> = it.take(5)
                    .map { song ->
                        deezerRepository.findSongByTitle(song.fullTitle ?: song.title)
                            .onErrorReturnItem(DeezerSong())
                    }

                Single.zip(deezerObservablesList) { arr: Array<Any> ->
                    arr.toList().map { it as DeezerSong }.filter { !it.title.isNullOrBlank() }
                }
            }
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)
}
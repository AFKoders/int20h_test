package com.afkoders.musicakinator.presentation.search

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.data.repository.DeezerRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import com.afkoders.musicakinator.presentation.interation_with_akinator.IntermixModel
import com.afkoders.musicakinator.presentation.interation_with_akinator.IntermixModelImpl
import com.afkoders.musicakinator.utils.speechRecognition.SpeechRecognitionHelper
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject


class SearchViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    private val deezerRepository: DeezerRepository,
    private val speechRecognitionHelper: SpeechRecognitionHelper,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {
    fun searchSongs(lyrics: String): Single<List<IntermixModelImpl>> =
        auddRepository
            .findSongsByLyrics(lyrics)
            .flatMap {
                val deezerObservablesList: List<Single<DeezerSong>> = it.take(5)
                    .map { song ->
                        deezerRepository.findSongByTitle(song.fullTitle ?: song.title)
                            .onErrorReturnItem(DeezerSong())
                    }

                // TODO AC. Create valid Intermix model.

                Single.zip(deezerObservablesList) { arr: Array<Any> ->
                    arr.toList().map { it as DeezerSong }.filter { !it.title.isNullOrBlank() }
                        .map { IntermixModelImpl(false, 0, "", "", "", "") }
                }
            }
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)

    fun stopListening() {
        speechRecognitionHelper.stopListening()
    }

    fun startListening(block: (text: String) -> Unit) {
        speechRecognitionHelper.setupRecognizingListener (block)
        speechRecognitionHelper.startListening()
    }
}
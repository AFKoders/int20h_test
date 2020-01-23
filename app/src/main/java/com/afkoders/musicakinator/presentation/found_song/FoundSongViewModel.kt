package com.afkoders.musicakinator.presentation.found_song

import android.content.Context
import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.repository.AuddRepository
import com.afkoders.musicakinator.di.qualifiers.SchedulerIO
import com.afkoders.musicakinator.di.qualifiers.SchedulerUI
import com.deezer.sdk.model.PaginatedList
import com.deezer.sdk.model.Permissions
import com.deezer.sdk.model.Track
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.connect.event.DialogListener
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.event.JsonRequestListener
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    @SchedulerUI private val schedulerUI: Scheduler,
    @SchedulerIO private val schedulerIO: Scheduler
) : ViewModel() {

    fun searchArtist(lyrics: String): Single<List<Song>> = auddRepository
            .findSongsByLyrics(lyrics)
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)

    fun searchTrackByName(applicationContext: Context, stringAuthor: String, stringSongName: String, deezerListener: JsonRequestListener) {
        val deezerConnect = DeezerConnect(applicationContext, "391104")

        val request = DeezerRequestFactory.requestSearchTracks("artist:\"$stringAuthor\" track:\"$stringSongName\"")
        deezerConnect.requestAsync(request, deezerListener)
    }
    
}
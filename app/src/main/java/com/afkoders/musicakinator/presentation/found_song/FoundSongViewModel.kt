package com.afkoders.musicakinator.presentation.found_song

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.afkoders.musicakinator.BuildConfig
import com.afkoders.musicakinator.R
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
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongViewModel @Inject constructor(
    private val auddRepository: AuddRepository,
    private @SchedulerUI val schedulerUI: Scheduler,
    private @SchedulerIO val schedulerIO: Scheduler
) : ViewModel() {

    fun searchArtist(lyrics: String): Single<List<Song>> = auddRepository
            .findSongsByLyrics(lyrics)
            .subscribeOn(schedulerIO)
            .observeOn(schedulerUI)

    fun searchTrackByName(applicationContext: Context, stringAuthor: String, stringSongName: String, deezerListener: JsonRequestListener) {
        val deezerConnect = DeezerConnect(applicationContext, "391104")

        val request = DeezerRequestFactory.requestSearchTracks("artist:\""+stringAuthor+"\" track:\""+stringSongName+"\"")
        deezerConnect.requestAsync(request, deezerListener)
    }
}
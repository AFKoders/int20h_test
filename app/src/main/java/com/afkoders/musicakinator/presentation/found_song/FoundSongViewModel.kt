package com.afkoders.musicakinator.presentation.found_song

import androidx.lifecycle.ViewModel
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.repository.AuddRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Kalevych Oleksandr on 2020-01-22.
 */

class FoundSongViewModel @Inject constructor(private val auddRepository: AuddRepository): ViewModel(){
    fun search(): Single<List<Song>> = auddRepository.findSongsByLyrics("jingle all the way all was fine it is to")
}
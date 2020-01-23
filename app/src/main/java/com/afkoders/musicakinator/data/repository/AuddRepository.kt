package com.afkoders.musicakinator.data.repository

import com.afkoders.musicakinator.data.models.Song
import io.reactivex.Single


interface AuddRepository {
    fun findSongsByLyrics(lyrics: String): Single<List<Song>>
}
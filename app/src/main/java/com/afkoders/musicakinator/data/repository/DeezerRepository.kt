package com.afkoders.musicakinator.data.repository

import com.afkoders.musicakinator.data.models.DeezerSong
import io.reactivex.Single

interface DeezerRepository {
    fun findSongByTitle(fullTitle: String): Single<DeezerSong>
}
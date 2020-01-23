package com.afkoders.musicakinator.data.repository

import com.afkoders.musicakinator.data.exception.NoResultException
import com.afkoders.musicakinator.data.models.DeezerSong
import com.afkoders.musicakinator.data.service.DeezerApiService
import io.reactivex.Single
import javax.inject.Inject

class DeezerRepositoryImpl @Inject constructor(private val apiService: DeezerApiService) :
    DeezerRepository {
    override fun findSongByTitle(fullTitle: String): Single<DeezerSong> {
        return apiService.findByArtistAndTrack(fullTitle)
            .map {
                if (!it.deezerSongs.isNullOrEmpty()) {
                    it.deezerSongs[0]
                } else {
                    throw NoResultException()
                }
            }
    }
}
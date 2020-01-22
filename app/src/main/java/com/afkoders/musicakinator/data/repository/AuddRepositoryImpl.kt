package com.afkoders.musicakinator.data.repository

import com.afkoders.musicakinator.BuildConfig
import com.afkoders.musicakinator.data.exception.NoResultException
import com.afkoders.musicakinator.data.models.Song
import com.afkoders.musicakinator.data.models.Status
import com.afkoders.musicakinator.data.service.AuddApiService
import io.reactivex.Single
import javax.inject.Inject

class AuddRepositoryImpl @Inject constructor(private val apiService: AuddApiService) : AuddRepository {
    override fun findSongsByLyrics(lyrics: String): Single<List<Song>> {
        return apiService.findByLyrics(lyrics, BuildConfig.AUDD_API_TOKEN).map {
            if ((it.status == Status.SUCCESS) && !it.result.isNullOrEmpty()) {
                it.result
            } else {
                throw NoResultException()
            }
        }
    }
}
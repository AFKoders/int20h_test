package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.data.models.FindDeezerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {
    @GET("/search/track")
    fun findByArtistAndTrack(
        @Query("q", encoded = true) query: String
    ): Single<FindDeezerResponse>
}
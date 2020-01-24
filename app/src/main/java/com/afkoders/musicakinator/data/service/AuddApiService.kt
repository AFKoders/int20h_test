package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.data.models.FindByLyricsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface AuddApiService {
    @GET("/findLyrics/")
    fun findByLyrics(
        @Query("q", encoded = true) lyrics: String,
        @Query("api_token") apiToken: String
    ): Single<FindByLyricsResponse>
}
package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.data.models.FindByLyricsResponse
import io.reactivex.Single
import retrofit2.http.*


interface AuddApiService {
    @GET("/findLyrics/")
    fun findByLyrics(
        @Query("q") lyrics: String,
        @Query("api_token") apiToken: String
    ): Single<FindByLyricsResponse>
}
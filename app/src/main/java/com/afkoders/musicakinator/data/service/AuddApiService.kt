package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.data.models.FindByLyricsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface AuddApiService {
    @GET("/findLyrics/?q={lyrics}")
    fun findByLyrics(@Path("lyrics") lyrics: String): Single<FindByLyricsResponse>
}
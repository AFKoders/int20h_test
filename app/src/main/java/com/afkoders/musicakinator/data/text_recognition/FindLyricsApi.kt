package com.afkoders.musicakinator.data.text_recognition

import com.afkoders.musicakinator.data.text_recognition.models.FindByLyricsResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface FindLyricsApi {
    @GET("{lyrics}")
    fun findByLyrics(@Path("lyrics") lyrics: String): FindByLyricsResponse
}
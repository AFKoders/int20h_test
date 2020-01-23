package com.afkoders.musicakinator.data.models

import com.google.gson.annotations.SerializedName


data class Song(
    @SerializedName("song_id")
    val songId: String,
    @SerializedName("artist_id")
    val artistId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("lyrics")
    val lyrics: String
)
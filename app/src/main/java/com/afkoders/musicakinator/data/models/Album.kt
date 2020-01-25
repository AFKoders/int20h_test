package com.afkoders.musicakinator.data.models

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("title") val title: String,
    @SerializedName("cover_medium") val picture: String
)
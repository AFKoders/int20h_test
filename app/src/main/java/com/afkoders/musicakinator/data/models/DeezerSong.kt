package com.afkoders.musicakinator.data.models

import com.google.gson.annotations.SerializedName

data class DeezerSong(
    @SerializedName("title") val title: String? = "",
    @SerializedName("link") val link: String? = "",
    @SerializedName("preview") val preview: String? = "",
    @SerializedName("album") val album: Album? = null
)
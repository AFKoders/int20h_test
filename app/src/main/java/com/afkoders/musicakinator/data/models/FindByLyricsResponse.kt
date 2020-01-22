package com.afkoders.musicakinator.data.models

import com.google.gson.annotations.SerializedName


data class FindByLyricsResponse(
    @SerializedName("status")
    val status: Status,
    @SerializedName("result")
    val result: List<RecognizedSongs>
)
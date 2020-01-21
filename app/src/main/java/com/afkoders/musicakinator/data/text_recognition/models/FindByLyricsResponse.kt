package com.afkoders.musicakinator.data.text_recognition.models

import com.afkoders.musicakinator.data.Status
import com.google.gson.annotations.SerializedName


data class FindByLyricsResponse(
    @SerializedName("status")
    val status: Status,
    @SerializedName("result")
    val result: List<RecognizedSongs>
)
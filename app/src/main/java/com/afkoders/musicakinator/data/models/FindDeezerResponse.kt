package com.afkoders.musicakinator.data.models

import com.google.gson.annotations.SerializedName

data class FindDeezerResponse(@SerializedName("data") val deezerSongs: List<DeezerSong>)
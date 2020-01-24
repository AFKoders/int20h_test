package com.afkoders.musicakinator.data.prefs

import java.io.Serializable

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

data class History(
    val openInDeezerLink: String,
    val trackId: Int,
    val trackName: String,
    val trackLyrics: String,
    val trackImage: String,
    val artistName: String,
    val time: String
) : Serializable
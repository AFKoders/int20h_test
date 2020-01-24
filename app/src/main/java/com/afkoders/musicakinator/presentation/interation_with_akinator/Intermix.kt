package com.afkoders.musicakinator.presentation.interation_with_akinator

import com.afkoders.musicakinator.data.prefs.History


open class Intermix(
    val openInDeezerLink: String,
    val trackId: Int,
    val trackName: String,
    val trackLyrics: String,
    val trackImage: String,
    val artistName: String
) {
    fun toHistory(time: String): History {
        return History(
            this.openInDeezerLink,
            this.trackId,
            this.trackName,
            this.trackLyrics,
            this.trackImage,
            this.artistName,
            time
        )
    }
}

object EmptyIntermix : Intermix("", -1, "", "", "", "")
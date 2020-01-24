package com.afkoders.musicakinator.data.prefs

import java.io.Serializable

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

data class HistoryModel (
    val name: String,
    val artist: String,
    val time: String
): Serializable
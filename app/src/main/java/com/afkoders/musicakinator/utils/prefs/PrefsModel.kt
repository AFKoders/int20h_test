package com.afkoders.musicakinator.utils.prefs

import java.io.Serializable

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

data class PrefsModel (
    val name: String,
    val artist: String,
    val time: String
): Serializable
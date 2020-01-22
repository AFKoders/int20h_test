package com.afkoders.musicakinator.utils.extensions.widget

import android.widget.Button


fun Button.disable() {
    isEnabled = false
}

fun Button.enable() {
    isEnabled = true
}
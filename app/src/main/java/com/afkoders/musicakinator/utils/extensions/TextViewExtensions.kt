package com.afkoders.musicakinator.utils.extensions

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText


fun TextView.erase() {
    this.text = ""
}
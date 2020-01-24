package com.afkoders.musicakinator.utils.extensions.widget

import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText

/**
 * Created by Kalevych Oleksandr on 2020-01-23.
 */

fun AppCompatEditText.addSearchWatcher(block: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) { block.invoke() }
        true
    }
}
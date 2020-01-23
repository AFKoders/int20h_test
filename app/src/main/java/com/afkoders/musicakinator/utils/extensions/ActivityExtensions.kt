package com.afkoders.musicakinator.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle


inline fun <reified T : Activity> Activity.openActivity(initializer: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java).apply { initializer() }
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.openActivityForResult(
    requestCode: Int,
    options: Bundle? = null,
    block: Intent.() -> Unit = {}
) {
    startActivityForResult(
        Intent(this, T::class.java).apply { block.invoke(this) },
        requestCode,
        options
    )
}
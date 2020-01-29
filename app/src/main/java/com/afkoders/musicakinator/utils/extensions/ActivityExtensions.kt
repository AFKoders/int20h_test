package com.afkoders.musicakinator.utils.extensions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ViewConfiguration


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

fun Activity.hasNavbar(): Boolean {

    // FIXME: This function and simular to this does not work.
    // TODO: provide method that would fix bottom padding on devices with hardware buttons.

    if (Build.FINGERPRINT.startsWith("generic"))
        return true

    val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
    return id > 0 && resources.getBoolean(id)
}

fun Activity.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

fun Activity.getNavigationBarHeight(): Int {
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0 && !hasMenuKey) resources.getDimensionPixelSize(resourceId) else 0
}
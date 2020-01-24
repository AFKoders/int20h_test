package com.afkoders.musicakinator.utils.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

inline fun Fragment.navigateTo(@IdRes destinationId: Int, block: Bundle.() -> Unit = {}) {
    findNavController().navigate(destinationId, Bundle().apply { block() })
}

fun Fragment.finish(@IdRes destinationId: Int? = null, inclusive: Boolean = false) {
    with((parentFragment as NavHostFragment).navController) {
        if (destinationId != null) {
            popBackStack(destinationId, inclusive)
        } else {
            popBackStack()
        }
    }
}
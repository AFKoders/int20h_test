package com.afkoders.musicakinator.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment


fun Fragment.finish(@IdRes destinationId: Int? = null, inclusive: Boolean = false) {
    with((parentFragment as NavHostFragment).navController) {
        if (destinationId != null) {
            popBackStack(destinationId, inclusive)
        } else {
            popBackStack()
        }
    }
}
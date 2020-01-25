package com.afkoders.musicakinator.utils.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast


fun Int.dpToPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()

fun Int.spToPx(): Int = (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()

fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    if (connectivityManager != null) {
        if (Build.VERSION.SDK_INT < 23) {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                return networkInfo.isConnected && (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
            }
        } else {
            val network = connectivityManager.activeNetwork
            if (network != null) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                return (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    ?: false) ||
                        (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            ?: false)
            }
        }
    }

    return false
}

fun Context.startIntentOrShowAlert(
    intent: Intent,
    errorDialogText: String
) {
    if ((intent.resolveActivity(this.packageManager) != null) && intent.resolveActivityInfo(
            this.packageManager,
            intent.flags
        ).exported
    ) {
        startActivity(intent)
    } else {
        Toast.makeText(this, errorDialogText, Toast.LENGTH_LONG).show()
    }
}
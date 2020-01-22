package com.afkoders.musicakinator.utils

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


fun screenWidth(): Int = Resources.getSystem().displayMetrics.widthPixels

fun screenHeight(): Int = Resources.getSystem().displayMetrics.heightPixels

fun buildVersionGE(version: Int): Boolean = Build.VERSION.SDK_INT >= version

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
                return (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false) ||
                        (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false)

            }
        }
    }

    return false
}
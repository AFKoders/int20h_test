package com.afkoders.musicakinator.utils

import android.content.res.Resources
import android.os.Build


fun screenWidth(): Int = Resources.getSystem().displayMetrics.widthPixels

fun screenHeight(): Int = Resources.getSystem().displayMetrics.heightPixels

fun buildVersionGE(version: Int): Boolean = Build.VERSION.SDK_INT >= version
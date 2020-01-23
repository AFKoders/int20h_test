package com.afkoders.musicakinator.presentation

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.afkoders.musicakinator.BuildConfig
import com.afkoders.musicakinator.R
import com.deezer.sdk.model.Permissions
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.connect.event.DialogListener
import com.deezer.sdk.network.request.event.JsonRequestListener
import dagger.android.support.DaggerAppCompatActivity
import java.lang.Exception
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.DeezerRequest




class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

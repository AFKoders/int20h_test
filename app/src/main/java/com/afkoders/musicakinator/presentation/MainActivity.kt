package com.afkoders.musicakinator.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import com.afkoders.musicakinator.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
    }
}

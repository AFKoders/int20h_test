package com.afkoders.musicakinator.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.afkoders.musicakinator.R
import com.afkoders.musicakinator.presentation.search.SearchFragment.Companion.RECORD_REQUEST_CODE
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("Permissions", "Permission has been denied by user")
                } else {
                    Log.i("Permissions", "Permission has been granted by user")
                }
            }
        }
    }
}

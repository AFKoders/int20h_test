package com.afkoders.musicakinator.presentation

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers


abstract class BaseActivity : AppCompatActivity() {

    /**
     * Allows to manipulate data on each onResume() call
     */
    abstract fun setupBindings()

    override fun onResume() {
        super.onResume()
        setupBindings()
    }

    internal fun getScheduler() = AndroidSchedulers.mainThread()
}
package com.afkoders.musicakinator

import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AkinatorApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}
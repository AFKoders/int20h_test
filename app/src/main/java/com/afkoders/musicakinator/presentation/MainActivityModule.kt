package com.afkoders.musicakinator.presentation

import android.content.Context
import com.afkoders.musicakinator.di.qualifiers.ActivityContext
import com.afkoders.musicakinator.di.scope.ActivityScope
import com.afkoders.musicakinator.di.scope.FragmentScope
import com.afkoders.musicakinator.presentation.found_song.FoundSongFragment
import com.afkoders.musicakinator.presentation.found_song.FoundSongModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [FoundSongModule::class])
    fun foundSongFragmentFragment(): FoundSongFragment?

    // TODO add other fragments

    @ActivityContext
    @Binds
    fun bindActivityContext(mainActivity: MainActivity): Context

    @Binds
    @ActivityScope
    fun bindMainActivity(mainActivity: MainActivity): DaggerAppCompatActivity
}
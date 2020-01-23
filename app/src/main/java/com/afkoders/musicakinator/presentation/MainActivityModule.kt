package com.afkoders.musicakinator.presentation

import android.content.Context
import com.afkoders.musicakinator.di.qualifiers.ActivityContext
import com.afkoders.musicakinator.di.scope.FragmentScope
import com.afkoders.musicakinator.presentation.found_song.FoundSongFragment
import com.afkoders.musicakinator.presentation.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector/*(modules = [FoundSongModule::class])*/
    fun foundSongFragment(): FoundSongFragment?

    @FragmentScope
    @ContributesAndroidInjector/*(modules = [FoundSongModule::class])*/
    fun searchFragment(): SearchFragment?

    // TODO add other fragments

    @ActivityContext
    @Binds
    fun bindActivityContext(mainActivity: MainActivity): Context
}
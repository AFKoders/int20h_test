package com.afkoders.musicakinator.presentation

import android.content.Context
import com.afkoders.musicakinator.di.qualifiers.ActivityContext
import com.afkoders.musicakinator.di.scope.FragmentScope
import com.afkoders.musicakinator.presentation.found_song.FoundSongFragment
import com.afkoders.musicakinator.presentation.history.HistoryFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.failure.FailureFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.loading.LoadingFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.retry.RetryFragment
import com.afkoders.musicakinator.presentation.interation_with_akinator.success.SuccessFragment
import com.afkoders.musicakinator.presentation.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun foundSongFragment(): FoundSongFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun successFragment(): SuccessFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun retryFragment(): RetryFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun failureFragment(): FailureFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun searchFragment(): SearchFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun historyFragment(): HistoryFragment?

    @FragmentScope
    @ContributesAndroidInjector
    fun loadingFragment(): LoadingFragment?

    // TODO add other fragments

    @ActivityContext
    @Binds
    fun bindActivityContext(mainActivity: MainActivity): Context
}
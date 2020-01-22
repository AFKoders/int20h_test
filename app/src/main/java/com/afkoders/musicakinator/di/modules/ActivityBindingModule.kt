package com.afkoders.musicakinator.di.modules

import com.afkoders.musicakinator.di.scope.ActivityScope
import com.afkoders.musicakinator.presentation.MainActivityModule
import com.afkoders.musicakinator.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivity(): MainActivity
}
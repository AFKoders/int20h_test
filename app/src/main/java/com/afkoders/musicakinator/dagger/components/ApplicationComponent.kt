package com.afkoders.musicakinator.dagger.components

import com.afkoders.musicakinator.dagger.modules.ApplicationModule
import com.afkoders.musicakinator.dagger.modules.NetworkingModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkingModule::class])
interface ApplicationComponent {
    // TODO: setup app component
}
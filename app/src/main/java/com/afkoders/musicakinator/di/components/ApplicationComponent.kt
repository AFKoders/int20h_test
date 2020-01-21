package com.afkoders.musicakinator.di.components

import com.afkoders.musicakinator.di.modules.ApplicationModule
import com.afkoders.musicakinator.di.modules.NetworkingModule
import com.afkoders.musicakinator.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkingModule::class]
)
interface ApplicationComponent : AndroidInjector<AkinatorApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AkinatorApplication>()
}
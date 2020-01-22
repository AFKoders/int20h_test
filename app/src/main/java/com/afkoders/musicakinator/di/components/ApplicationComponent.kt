package com.afkoders.musicakinator.di.components

import com.afkoders.musicakinator.AkinatorApplication
import com.afkoders.musicakinator.data.repository.RepositoryModule
import com.afkoders.musicakinator.di.modules.ApplicationModule
import com.afkoders.musicakinator.di.modules.NetworkingModule
import com.afkoders.musicakinator.di.modules.RxModule
import com.afkoders.musicakinator.di.scope.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        RxModule::class,
        RepositoryModule::class,
        NetworkingModule::class]
)
interface ApplicationComponent : AndroidInjector<AkinatorApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AkinatorApplication>()
}
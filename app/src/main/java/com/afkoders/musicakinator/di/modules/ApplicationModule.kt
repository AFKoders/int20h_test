package com.afkoders.musicakinator.di.modules

import android.app.Application
import android.content.Context
import com.afkoders.musicakinator.AkinatorApplication
import com.afkoders.musicakinator.di.qualifiers.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity


@Module(includes = [ActivityBindingModule::class])
abstract class ApplicationModule {
    @Provides
    fun provideApplication(akinatorApplication: AkinatorApplication): Application {
        return akinatorApplication
    }

    @Binds
    @ApplicationContext
    abstract fun bindContext(akinatorApplication: AkinatorApplication): Context
}
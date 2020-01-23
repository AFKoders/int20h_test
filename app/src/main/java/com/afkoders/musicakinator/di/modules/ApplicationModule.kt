package com.afkoders.musicakinator.di.modules

import android.app.Application
import android.content.Context
import com.afkoders.musicakinator.AkinatorApplication
import com.afkoders.musicakinator.data.service.AuddApiServiceModule
import com.afkoders.musicakinator.data.service.DeezerApiServiceModule
import com.afkoders.musicakinator.di.qualifiers.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [ActivityBindingModule::class, DeezerApiServiceModule::class, AuddApiServiceModule::class])
abstract class ApplicationModule {

    @Binds
    @ApplicationContext
    abstract fun bindContext(akinatorApplication: AkinatorApplication): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideApplication(akinatorApplication: AkinatorApplication): Application {
            return akinatorApplication
        }
    }
}
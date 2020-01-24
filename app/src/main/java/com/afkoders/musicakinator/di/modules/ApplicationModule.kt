package com.afkoders.musicakinator.di.modules

import android.app.Application
import android.content.Context
import com.afkoders.musicakinator.AkinatorApplication
import com.afkoders.musicakinator.data.prefs.HistoryPrefs
import com.afkoders.musicakinator.data.service.AuddApiServiceModule
import com.afkoders.musicakinator.data.service.DeezerApiServiceModule
import com.afkoders.musicakinator.di.qualifiers.ApplicationContext
import com.afkoders.musicakinator.di.scope.ApplicationScope
import com.afkoders.musicakinator.utils.speechRecognition.SpeechRecognitionHelper
import com.google.gson.Gson
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

        @Provides
        @ApplicationScope
        @JvmStatic
        fun provideHistoryPrefs(@ApplicationContext context: Context, gson: Gson): HistoryPrefs {
            return HistoryPrefs(context, gson)
        }

        @Provides
        @ApplicationScope
        @JvmStatic
        fun provideSpeechRecognition(@ApplicationContext context: Context): SpeechRecognitionHelper {
            return SpeechRecognitionHelper(context)
        }
    }
}
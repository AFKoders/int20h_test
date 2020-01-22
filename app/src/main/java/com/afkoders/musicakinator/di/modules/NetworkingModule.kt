package com.afkoders.musicakinator.di.modules

import android.content.Context
import com.afkoders.musicakinator.data.retrofit.ConnectivityInterceptor
import com.afkoders.musicakinator.di.qualifiers.ApplicationContext
import com.afkoders.musicakinator.di.qualifiers.PlayerQualifier
import com.afkoders.musicakinator.di.qualifiers.SearchQualifier
import com.afkoders.musicakinator.di.scope.ApplicationScope
import com.afkoders.musicakinator.utils.NullOrEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkingModule {
    companion object {
        private const val CONNECT_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 1L

        private const val CACHE_SIZE = 10 * 1000 * 1000L // 10Mb
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(cacheFile: File) = Cache(cacheFile, CACHE_SIZE)

    @Provides
    @ApplicationScope
    fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor =
        ConnectivityInterceptor(context)

    @Provides
    fun provideChuckInterceptor(@ApplicationContext context: Context) = ChuckInterceptor(context)

    @Provides
    @ApplicationScope
    fun provideCacheFile(@ApplicationContext context: Context): File {
        val cacheFile = File(context.cacheDir, "okhttp_cache")
        cacheFile.mkdirs()

        return cacheFile
    }

    @Provides
    @ApplicationScope
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, CACHE_SIZE)

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(
        connectivityInterceptor: ConnectivityInterceptor,
        chuckInterceptor: ChuckInterceptor,
        cache: Cache?
    ): OkHttpClient? {
        return OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(chuckInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.HOURS)
            .readTimeout(READ_TIMEOUT, TimeUnit.HOURS)
            .cache(cache)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()
}
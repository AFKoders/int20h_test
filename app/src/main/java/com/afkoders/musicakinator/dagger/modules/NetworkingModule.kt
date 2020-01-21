package com.afkoders.musicakinator.dagger.modules

import com.afkoders.musicakinator.dagger.qualifiers.PlayerQualifier
import com.afkoders.musicakinator.dagger.qualifiers.SearchQualifier
import com.afkoders.musicakinator.utils.NullOrEmptyConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkingModule(
    private val searchUrl: String,
    private val playerUrl: String
) {
    private val CONNECT_TIMEOUT = 30L
    private val READ_TIMEOUT = 30L
    private val CACHE_SIZE = 10 * 1000 * 1000L

    // TODO: setup OkHttp client
    fun provideClient() {}

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideOkHttpCache(cacheFile: File) = Cache(cacheFile, CACHE_SIZE)

    @Provides
    @Singleton
    @SearchQualifier
    fun provideSearchRetrofitBuilder(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(searchUrl)
        .addConverterFactory(NullOrEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    @PlayerQualifier
    fun providePlayerhRetrofitBuilder(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(playerUrl)
        .addConverterFactory(NullOrEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
}
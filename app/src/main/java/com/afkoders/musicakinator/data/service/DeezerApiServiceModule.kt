package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.BuildConfig
import com.afkoders.musicakinator.di.modules.NetworkingModule
import com.afkoders.musicakinator.di.qualifiers.Deezer
import com.afkoders.musicakinator.di.scope.ApplicationScope
import com.afkoders.musicakinator.utils.NullOrEmptyConverterFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkingModule::class])
class DeezerApiServiceModule {

    @Provides
    @ApplicationScope
    fun provideDeezerApiService(@Deezer deezerApiRetrofit: Retrofit): DeezerApiService {
        return deezerApiRetrofit.create(DeezerApiService::class.java)
    }

    @Provides
    @ApplicationScope
    @Deezer
    fun provideDeezerRetrofitBuilder(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.DEEZER_HOST)
        .addConverterFactory(NullOrEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

}
package com.afkoders.musicakinator.data.service

import com.afkoders.musicakinator.BuildConfig
import com.afkoders.musicakinator.di.modules.NetworkingModule
import com.afkoders.musicakinator.di.qualifiers.Audd
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
class AuddApiServiceModule {
    @Provides
    @ApplicationScope
    fun provideAuddApiService(@Audd auddApiRetrofit: Retrofit): AuddApiService {
        return auddApiRetrofit.create(AuddApiService::class.java)
    }

    @Provides
    @ApplicationScope
    @Audd
    fun provideAuddRetrofitBuilder(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.AUDD_IO_HOST)
        .addConverterFactory(NullOrEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
}
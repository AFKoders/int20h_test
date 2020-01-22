package com.afkoders.musicakinator.data.repository

import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun provideAuddRepository(auddApiRepository: AuddRepositoryImpl): AuddRepository
}
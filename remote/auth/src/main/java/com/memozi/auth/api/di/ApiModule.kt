package com.memozi.auth.api.di

import com.memozi.auth.api.AuthService
import com.memozi.network.di.Auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesRecordyApi(@Auth retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}

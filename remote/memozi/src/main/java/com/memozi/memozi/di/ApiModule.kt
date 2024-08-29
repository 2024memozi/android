package com.memozi.memozi.di

import com.memozi.memozi.api.MemoziApi
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
    fun providesMemoziApi(@Auth retrofit: Retrofit): MemoziApi =
        retrofit.create(MemoziApi::class.java)
}

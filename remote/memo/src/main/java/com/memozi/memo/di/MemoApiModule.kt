package com.memozi.memo.di

import com.memozi.memo.api.MemoService
import com.memozi.network.di.Auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemoApiModule {
    @Provides
    @Singleton
    fun provideMemoApi(@Auth retrofit: Retrofit): MemoService =
        retrofit.create(MemoService::class.java)
}

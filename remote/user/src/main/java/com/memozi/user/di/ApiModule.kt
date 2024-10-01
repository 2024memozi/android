package com.memozi.user.di

import com.memozi.network.di.Auth
import com.memozi.user.UserService
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
    fun providesUserApi(@Auth retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}

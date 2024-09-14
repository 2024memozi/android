package com.memozi.memo.di

import com.memozi.memo.api.CategoryService
import com.memozi.network.di.Auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryApiModule {
    @Provides
    @Singleton
    fun provideCategoryApi(@Auth retrofit: Retrofit): CategoryService =
        retrofit.create(CategoryService::class.java)
}


package com.memozi.diary.di

import com.memozi.diary.api.DiaryService
import com.memozi.network.di.Auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiaryApiModule {
    @Provides
    @Singleton
    fun provideDiaryApi(@Auth retrofit: Retrofit): DiaryService =
        retrofit.create(DiaryService::class.java)
}

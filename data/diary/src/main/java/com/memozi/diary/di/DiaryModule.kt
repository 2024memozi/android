package com.memozi.diary.di

import com.memozi.diary.repository.DiaryRepository
import com.memozi.diary.repository.DiaryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiaryModule {
    @Binds
    @Singleton
    abstract fun bindsDiaryRepository(diaryRepository: DiaryRepositoryImpl): DiaryRepository
}

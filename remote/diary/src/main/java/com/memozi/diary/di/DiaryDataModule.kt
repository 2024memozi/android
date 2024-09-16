package com.memozi.diary.di

import com.memozi.diary.datasource.DiaryRemoteDataSourceImpl
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiaryDataModule {
    @Binds
    @Singleton
    abstract fun bindsDiaryRemoteDataSource(diaryRemoteDataSource: DiaryRemoteDataSourceImpl): DiaryRemoteDataSource
}

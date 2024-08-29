package com.memozi.memozi.di

import com.memozi.memozi.source.remote.MemoziRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindsMemoziRemoteDataSource(memoziRemoteDataSource: MemoziRemoteDataSource): MemoziRemoteDataSource
}

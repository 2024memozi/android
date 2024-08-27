package com.memozi.memozi.di

import com.memozi.data.source.local.MemoziLocalDataSource
import com.memozi.memozi.datasource.MemoziLocalDataSourceImpl
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
    abstract fun bindsMemoziLocalDataSource(memoziLocalDataSourceImpl: MemoziLocalDataSourceImpl): MemoziLocalDataSource
}

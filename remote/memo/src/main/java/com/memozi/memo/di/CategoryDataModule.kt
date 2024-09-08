package com.memozi.memo.di

import com.memozi.memo.datasource.MemoRemoteDataSourceImpl
import com.memozi.memo.source.remote.MemoRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryDataModule{
    @Binds
    @Singleton
    abstract fun bindsMemoRemoteDataSource(memoRemoteDataSource: MemoRemoteDataSourceImpl): MemoRemoteDataSource
}

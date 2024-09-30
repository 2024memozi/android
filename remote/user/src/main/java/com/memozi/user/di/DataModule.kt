package com.memozi.user.di

import com.memozi.auth.source.remote.UserRemoteDataSource
import com.memozi.user.datasource.UserRemoteDataSourceImpl
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
    abstract fun bindsUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSourceImpl): UserRemoteDataSource
}

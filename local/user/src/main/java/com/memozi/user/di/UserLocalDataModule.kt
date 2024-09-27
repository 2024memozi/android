package com.memozi.user.di

import com.memozi.auth.source.local.UserLocalDataSource
import com.memozi.user.datasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserLocalDataModule {
    @Binds
    @Singleton
    abstract fun bindsUserLocalDataSource(userLocalDataSource: UserLocalDataSourceImpl): UserLocalDataSource
}

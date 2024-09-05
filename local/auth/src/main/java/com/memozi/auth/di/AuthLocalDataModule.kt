package com.memozi.auth.di

import com.memozi.auth.datasource.AuthLocalDataSourceImpl
import com.memozi.auth.source.local.AuthLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthLocalDataModule {
    @Binds
    @Singleton
    abstract fun bindsAuthLocalDataSource(authLocalDataSource: AuthLocalDataSourceImpl): AuthLocalDataSource
}

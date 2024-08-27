package com.memozi.memozi.di

import com.memozi.memozi.domain.repository.MemoziRepository
import com.memozi.memozi.repository.MemoziRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MemoziModule {
    @Binds
    @Singleton
    abstract fun bindsMemoziRepository(memoziRepositoryImpl: MemoziRepositoryImpl): MemoziRepository
}

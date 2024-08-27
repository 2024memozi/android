package com.memozi.data.di

import com.memozi.data.repository.MemoziRepositoryImpl
import com.memozi.domain.repository.MemoziRepository
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

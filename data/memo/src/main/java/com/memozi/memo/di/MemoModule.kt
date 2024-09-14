package com.memozi.memo.di

import com.memozi.memo.repository.MemoRepository
import com.memozi.memo.repository.MemoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MemoModule {
    @Binds
    @Singleton
    abstract fun bindsMemoRepository(memoRepository: MemoRepositoryImpl): MemoRepository
}

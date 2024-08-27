package com.memozi.common.di

import com.memozi.common.security.SecurityInterface
import com.memozi.common.security.SecurityUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SecurityModule {
    @Singleton
    @Binds
    abstract fun bindsSecurityUtil(securityUtil: SecurityUtil): SecurityInterface
}

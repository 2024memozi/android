package com.memozi.oauth.di

import com.memozi.oauth.OAuthInteractor
import com.memozi.oauth.repository.KakaoAuthManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class KakaoAuthModule {
    @Binds
    @ActivityScoped
    abstract fun provideKakaoAuthRepository(
        kakaoAuthInteractor: KakaoAuthManager
    ): OAuthInteractor
}

package com.memozi.oauth

import com.memozi.model.UserEntity
import com.memozi.oauth.model.KakaoToken

interface OAuthInteractor {
    suspend fun loginByKakao(): Result<KakaoToken>
    fun logout()
    fun withdraw()
    suspend fun getUser(): Result<UserEntity>
}

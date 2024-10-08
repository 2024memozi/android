package com.memozi.auth.repository

import com.memozi.model.AuthEntity
import com.memozi.model.UserEntity

interface AuthRepository {
    suspend fun signIn(accessToken: String): Result<AuthEntity>
    suspend fun delete(): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun saveLocalData(authEntity: AuthEntity): Result<Unit>
    suspend fun getLocalData(): Result<AuthEntity>

    suspend fun saveUserData(userEntity: UserEntity): Result<Unit>
    suspend fun getUserData(): Result<UserEntity>
}

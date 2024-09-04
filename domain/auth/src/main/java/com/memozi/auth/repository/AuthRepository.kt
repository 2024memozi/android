package com.memozi.auth.repository

import com.memozi.model.AuthEntity

interface AuthRepository {
    suspend fun signIn(accessToken: String): Result<AuthEntity>
    suspend fun delete(): Result<Unit>
    suspend fun logout(): Result<Unit>
}

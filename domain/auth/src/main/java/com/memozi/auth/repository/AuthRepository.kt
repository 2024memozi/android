package com.memozi.auth.repository

interface AuthRepository {
    suspend fun signIn(accessToken: String): Result<String>
    suspend fun delete(): Result<Unit>
    suspend fun logout(): Result<Unit>
}

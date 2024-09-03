package com.memozi.auth.source.remote

interface AuthRemoteDataSource {
    suspend fun signIn(accessToken: String): String
    suspend fun delete()
    suspend fun logout()
}

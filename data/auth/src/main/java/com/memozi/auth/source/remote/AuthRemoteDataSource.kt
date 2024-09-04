package com.memozi.auth.source.remote

import com.memozi.auth.model.response.ResponseSignInDto

interface AuthRemoteDataSource {
    suspend fun signIn(accessToken: String): ResponseSignInDto
    suspend fun delete()
    suspend fun logout()
}

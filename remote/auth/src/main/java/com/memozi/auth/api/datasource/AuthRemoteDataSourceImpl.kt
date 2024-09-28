package com.memozi.auth.api.datasource

import com.memozi.auth.api.AuthService
import com.memozi.auth.model.request.RequestSignInDto
import com.memozi.auth.source.remote.AuthRemoteDataSource
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun signIn(accessToken: String) = authService.signIn(RequestSignInDto(accessToken))
    override suspend fun delete() {
        authService.delete()
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}

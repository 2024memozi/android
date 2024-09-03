package com.memozi.auth.api.datasource

import com.memozi.auth.api.AuthService
import com.memozi.auth.source.remote.AuthRemoteDataSource
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun signIn(accessToken: String) = authService.signIn(accessToken)
    override suspend fun delete() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}

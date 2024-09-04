package com.memozi.auth.repository

import com.memozi.auth.model.response.toModel
import com.memozi.auth.source.remote.AuthRemoteDataSource
import com.memozi.model.AuthEntity
import com.memozi.model.exception.ApiError
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun signIn(accessToken: String): Result<AuthEntity> = runCatching {
        authRemoteDataSource.signIn(accessToken)
    }.mapCatching {
        it.toModel()
    }.recoverCatching { exception ->
        when (exception) {
            is IOException -> {
                throw ApiError("IOException")
            }

            else -> {
                throw exception
            }
        }
    }

    override suspend fun delete(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }
}

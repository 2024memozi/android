package com.memozi.auth.repository

import com.memozi.auth.model.response.toModel
import com.memozi.auth.source.local.AuthLocalDataSource
import com.memozi.auth.source.local.UserLocalDataSource
import com.memozi.auth.source.remote.AuthRemoteDataSource
import com.memozi.datastore.token.AuthToken
import com.memozi.datastore.user.User
import com.memozi.model.AuthEntity
import com.memozi.model.UserEntity
import com.memozi.model.exception.ApiError
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val authLocalDataSoruce: AuthLocalDataSource
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

    override suspend fun delete(): Result<Unit> = runCatching {
        authRemoteDataSource.delete()
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        authLocalDataSoruce.setAuthLocalData(AuthToken("", ""))
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

    override suspend fun saveLocalData(authToken: AuthEntity): Result<Unit> = runCatching {
        authLocalDataSoruce.setAuthLocalData(
            AuthToken(
                authToken.accessToken,
                authToken.refreshToken
            )
        )
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

    override suspend fun getLocalData(): Result<AuthEntity> =
        runCatching {
            AuthEntity(
                authLocalDataSoruce.authLocalData.first().accessToken,
                authLocalDataSoruce.authLocalData.first().refreshToken
            )
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

    override suspend fun saveUserData(userEntity: UserEntity): Result<Unit> = runCatching {
        userLocalDataSource.setUserLocalData(User(userEntity.email, userEntity.nickname))
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

    override suspend fun getUserData(): Result<UserEntity> = runCatching {
        UserEntity(
            userLocalDataSource.userLocalData.first().email,
            userLocalDataSource.userLocalData.first().nickname
        )
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
}

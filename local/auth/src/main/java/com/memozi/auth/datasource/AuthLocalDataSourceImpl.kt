package com.memozi.auth.datasource

import com.memozi.auth.source.local.AuthLocalDataSource
import com.memozi.datastore.token.AuthToken
import com.memozi.datastore.token.TokenDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val authLocalDataSource: TokenDataStore
) : AuthLocalDataSource {
    override val authLocalData: Flow<AuthToken> = authLocalDataSource.token
    override suspend fun setAuthLocalData(authToken: AuthToken) {
        authLocalDataSource.setAuthToken(authToken = authToken)
    }
}

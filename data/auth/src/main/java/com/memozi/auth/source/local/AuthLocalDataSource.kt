package com.memozi.auth.source.local

import com.memozi.datastore.token.AuthToken
import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    val authLocalData: Flow<AuthToken>
    suspend fun setAuthLocalData(authToken: AuthToken)
}

package com.memozi.auth.source.local

import com.memozi.datastore.user.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val userLocalData: Flow<User>
    suspend fun setUserLocalData(user: User)
}

package com.memozi.user.datasource

import com.memozi.auth.source.local.UserLocalDataSource
import com.memozi.datastore.user.User
import com.memozi.datastore.user.UserDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userLocalDataSource: UserDataStore
) : UserLocalDataSource {
    override val userLocalData: Flow<User> = userLocalDataSource.user
    override suspend fun setUserLocalData(user: User) {
        userLocalDataSource.setUser(user)
    }
}

package com.memozi.datastore.user

import android.util.Log
import androidx.datastore.core.DataStore
import java.io.IOException
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val userPreferences: DataStore<User>
) {
    val user = userPreferences.data

    suspend fun setUser(user: User) {
        try {
            userPreferences.updateData {
                it.copy(email = user.email, nickname = user.nickname)
            }
        } catch (ioException: IOException) {
            Log.e("exception user", "ioException")
        }
    }
}

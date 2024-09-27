package com.memozi.datastore.user

import android.util.Log
import androidx.datastore.core.DataStore
import java.io.IOException
import javax.inject.Inject

class UserDataStore @Inject constructor(
    private val tokenPreferences: DataStore<User>
) {
    val token = tokenPreferences.data

    suspend fun setUser(user: User) {
        try {
            tokenPreferences.updateData {
                it.copy(email = user.email, nickname = user.nickname)
            }
        } catch (ioException: IOException) {
            Log.e("exception user", "ioException")
        }
    }
}

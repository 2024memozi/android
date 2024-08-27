package com.memozi.memozi.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import com.memozi.datastore.MemoziLocalData
import com.memozi.memozi.source.local.MemoziLocalDataSource
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class MemoziLocalDataSourceImpl @Inject constructor(
    private val userPreferences: DataStore<MemoziLocalData>
) : MemoziLocalDataSource {
    override val memoziLocalData: Flow<MemoziLocalData> = userPreferences.data

    override suspend fun setMemoziLocalData(memoziData: MemoziLocalData) {
        try {
            userPreferences.updateData {
                it.copy(memoziData.a, memoziData.b)
            }
        } catch (ioException: IOException) {
            Log.e("exception", "ioException")
        }
    }
}

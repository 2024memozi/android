package com.memozi.memozi.source.local

import com.memozi.datastore.MemoziLocalData
import kotlinx.coroutines.flow.Flow

interface MemoziLocalDataSource {
    val memoziLocalData: Flow<MemoziLocalData>
    suspend fun setMemoziLocalData(memoziData: MemoziLocalData)
}

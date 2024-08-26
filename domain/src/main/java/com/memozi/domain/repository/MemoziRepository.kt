package com.memozi.domain.repository

import com.memozi.domain.model.Memozi
import com.memozi.model.CoreData
import kotlinx.coroutines.flow.Flow

interface MemoziRepository {
    suspend fun getMemozi(): Result<Memozi>
    suspend fun postMemozi(memozi: Memozi): Result<Unit>
    fun getData(): Flow<CoreData>
}

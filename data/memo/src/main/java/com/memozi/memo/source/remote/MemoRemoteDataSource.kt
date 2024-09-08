package com.memozi.memo.source.remote

import com.memozi.memo.model.Category

interface MemoRemoteDataSource {
//    suspend fun delCategory(categoryId: Int): Result<Unit>
//    suspend fun putCategory(categoryId: Int): Result<Category>
//    suspend fun getCategory(categoryId: Int): Result<Category>
//
//    suspend fun getCategory(): Result<List<Category>>
    suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorId: Int,
        txtColorId: Int
    ): Unit

//    suspend fun getCategorySearch(): Result<List<Category>>
}


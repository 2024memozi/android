package com.memozi.memo.repository

import com.memozi.memo.model.Category

interface MemoRepository {
    suspend fun delCategory(categoryId: Int): Result<Unit>
    suspend fun putCategory(categoryId: Int): Result<Category>
    suspend fun getCategory(categoryId: Int): Result<Category>

    suspend fun getCategory(
        page: Int,
        size: Int,
        sort: List<String>
    ): Result<List<Category>>

    suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String
    ): Result<Unit>

    suspend fun getCategorySearch(): Result<List<Category>>
}

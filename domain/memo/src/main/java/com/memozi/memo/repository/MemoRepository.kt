package com.memozi.memo.repository

import com.memozi.memo.model.Category
import com.memozi.memo.model.CheckBox
import com.memozi.memo.model.Memo
import com.memozi.memo.model.SearchResult

interface MemoRepository {
    suspend fun delCategory(categoryId: Int): Result<Unit>
    suspend fun updateCategory(
        categoryId: Int,
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    ): Result<Category>

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
        image: String?
    ): Result<Unit>

    suspend fun getCategorySearch(query: String): Result<SearchResult>

    suspend fun putMemo(
        categoryId: Int,
        title: String,
        content: String,
        checkBoxs: List<CheckBox>
    ): Result<Memo>
}

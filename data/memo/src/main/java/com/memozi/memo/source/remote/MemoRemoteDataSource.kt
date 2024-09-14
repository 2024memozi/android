package com.memozi.memo.source.remote

import com.memozi.memo.model.request.RequestCheckBox
import com.memozi.memo.model.response.ResponseCategory
import com.memozi.memo.model.response.ResponseCategoryByID
import com.memozi.memo.model.response.ResponseMemo
import com.memozi.memo.model.response.ResponseSearch

interface MemoRemoteDataSource {

    suspend fun putMemo(
        categoryId: Int,
        title: String,
        content: String,
        checkBoxs: List<RequestCheckBox>
    ): ResponseMemo

    suspend fun getCategory(
        categoryId: Int,
        page: Int,
        size: Int,
        sort: List<String>
    ): ResponseCategoryByID

    suspend fun getCategory(
        page: Int,
        size: Int,
        sort: List<String>
    ): List<ResponseCategory>

    suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    )

    suspend fun updateCategory(
        categoryId: Int,
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    ): ResponseCategory

    suspend fun delCategory(categoryId: Int)
    suspend fun getCategorySearch(query: String): List<ResponseSearch>
}

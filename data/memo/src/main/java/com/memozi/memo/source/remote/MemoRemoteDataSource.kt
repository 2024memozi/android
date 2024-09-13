package com.memozi.memo.source.remote

import com.memozi.memo.model.response.ResponseCategory
import okhttp3.MultipartBody

interface MemoRemoteDataSource {
//    suspend fun delCategory(categoryId: Int): Result<Unit>
//    suspend fun putCategory(categoryId: Int): Result<Category>
//    suspend fun getCategory(categoryId: Int): Result<Category>
//
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
        image: MultipartBody.Part?
    ): Unit

//    suspend fun getCategorySearch(): Result<List<Category>>
}

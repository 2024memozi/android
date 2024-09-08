package com.memozi.memo.datasource

import com.memozi.memo.api.CategoryService
import com.memozi.memo.source.remote.MemoRemoteDataSource
import javax.inject.Inject

class MemoRemoteDataSourceImpl @Inject constructor(
    private val categoryService: CategoryService
) : MemoRemoteDataSource {

    override suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorId: Int,
        txtColorId: Int
    ) {
        categoryService.postCategory(name, defaultImageUrl, bgColorId, txtColorId)
    }
}

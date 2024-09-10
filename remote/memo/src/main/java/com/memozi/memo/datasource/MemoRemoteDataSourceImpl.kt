package com.memozi.memo.datasource

import com.memozi.memo.api.CategoryService
import com.memozi.memo.model.request.RequestPageable
import com.memozi.memo.model.response.ResponseCategory
import com.memozi.memo.source.remote.MemoRemoteDataSource
import javax.inject.Inject

class MemoRemoteDataSourceImpl @Inject constructor(
    private val categoryService: CategoryService
) : MemoRemoteDataSource {

    override suspend fun getCategory(
        page: Int,
        size: Int,
        sort: List<String>
    ): List<ResponseCategory> =
        categoryService.getAllCategories(pageable = RequestPageable(page, size, sort))

    override suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorId: Int,
        txtColorId: Int
    ) {
        categoryService.postCategory(name, defaultImageUrl, bgColorId, txtColorId)
    }
}

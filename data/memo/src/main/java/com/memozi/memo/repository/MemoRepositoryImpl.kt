package com.memozi.memo.repository

import android.util.Log
import com.memozi.memo.model.Category
import com.memozi.memo.source.remote.MemoRemoteDataSource
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoRemoteDataSource: MemoRemoteDataSource
) : MemoRepository {
    override suspend fun delCategory(categoryId: Int): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun putCategory(categoryId: Int): Result<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategory(categoryId: Int): Result<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategory(): Result<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorId: Int,
        txtColorId: Int
    ){
        memoRemoteDataSource.postCategory(name, defaultImageUrl, bgColorId, txtColorId)
            .runCatching {
                Log.d("postCategory", "postCategory: 성공")
            }
    }

    override suspend fun getCategorySearch(): Result<List<Category>> {
        TODO("Not yet implemented")
    }

}
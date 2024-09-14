package com.memozi.memo.repository

import com.memozi.memo.model.Category
import com.memozi.memo.model.SearchResult
import com.memozi.memo.model.response.toDomain
import com.memozi.memo.source.remote.MemoRemoteDataSource
import com.memozi.model.exception.ApiError
import retrofit2.HttpException
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoRemoteDataSource: MemoRemoteDataSource
) : MemoRepository {
    override suspend fun delCategory(categoryId: Int): Result<Unit> = runCatching {
        memoRemoteDataSource.delCategory(categoryId)
    }

    override suspend fun updateCategory(
        categoryId: Int,
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    ): Result<Category> = runCatching {
        memoRemoteDataSource.updateCategory(categoryId, name, defaultImageUrl, bgColorImageUrl, txtColor, image)
    }.mapCatching {
        it.toDomain()
    }

    override suspend fun getCategory(categoryId: Int): Result<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategory(
        page: Int,
        size: Int,
        sort: List<String>
    ): Result<List<Category>> = runCatching {
        memoRemoteDataSource.getCategory(page, size, sort)
    }.mapCatching {
        it.map { it.toDomain() }
    }.recoverCatching { exception ->
        when (exception) {
            is HttpException -> {
                throw ApiError(exception.message())
            }

            else -> {
                throw exception
            }
        }
    }

    override suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    ) = runCatching {
        memoRemoteDataSource.postCategory(name, defaultImageUrl, bgColorImageUrl, txtColor, image)
    }.recoverCatching { exception ->
        when (exception) {
            is HttpException -> {
                throw ApiError(exception.message())
            }

            else -> {
                throw exception
            }
        }
    }
    override suspend fun getCategorySearch(query:String): Result<SearchResult> = runCatching {
        memoRemoteDataSource.getCategorySearch(query)
    }.mapCatching {
        it.toDomain()
    }
}

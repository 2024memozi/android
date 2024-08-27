package com.memozi.data.repository

import com.memozi.data.model.remote.reponse.toDomain
import com.memozi.data.model.remote.request.toData
import com.memozi.data.source.local.MemoziLocalDataSource
import com.memozi.data.source.remote.MemoziRemoteDataSource
import com.memozi.domain.model.Memozi
import com.memozi.domain.repository.MemoziRepository
import com.memozi.model.CoreData
import com.memozi.model.exception.ApiError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class MemoziRepositoryImpl @Inject constructor(
    private val memoziLocalDataSource: MemoziLocalDataSource,
    private val memoziRemoteDataSource: MemoziRemoteDataSource
) : MemoziRepository {
    override suspend fun getMemozi(): Result<Memozi> =
        runCatching { memoziRemoteDataSource.getMemozi().data!! }
            .mapCatching { it.toDomain() }
            .recoverCatching { exception ->
                when (exception) {
                    is HttpException -> {
                        when (exception.code()) {
                            in 300..399 -> throw ApiError("엥")
                            400 -> throw ApiError("무슨무슨 오류")
                            401 -> throw ApiError("토큰 오류")
                            402 -> throw ApiError("무슨무슨 오류")
                            403 -> throw ApiError("무슨무슨 오류")
                            404 -> throw ApiError("무슨무슨 오류")
                            else -> throw ApiError("서버오류")
                        }
                    }

                    else -> throw exception
                }
            }

    override suspend fun postMemozi(memozi: Memozi): Result<Unit> =
        runCatching { memoziRemoteDataSource.postMemozi(memozi.toData()).data!! }
            .recoverCatching { exception ->
                when (exception) {
                    is HttpException -> {
                        when (exception.code()) {
                            in 300..399 -> throw ApiError("엥")
                            400 -> throw ApiError("무슨무슨 오류")
                            401 -> throw ApiError("토큰 오류")
                            402 -> throw ApiError("무슨무슨 오류")
                            403 -> throw ApiError("무슨무슨 오류")
                            404 -> throw ApiError("무슨무슨 오류")
                            else -> throw ApiError("서버오류")
                        }
                    }

                    else -> throw exception
                }
            }

    override fun getData(): Flow<CoreData> = memoziLocalDataSource.memoziLocalData.map { it ->
        CoreData(it.a, it.b)
    }
}

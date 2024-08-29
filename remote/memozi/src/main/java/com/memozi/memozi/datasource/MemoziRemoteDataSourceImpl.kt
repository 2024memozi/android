package com.memozi.memozi.datasource

import com.memozi.memozi.api.MemoziApi
import com.memozi.memozi.model.remote.reponse.ResponseGetMemoziDto
import com.memozi.memozi.model.remote.request.RequestPostMemoziDto
import com.memozi.memozi.source.remote.MemoziRemoteDataSource
import com.memozi.network.model.BaseResponse
import javax.inject.Inject

class MemoziRemoteDataSourceImpl @Inject constructor(
    private val memoziApi: MemoziApi
) : MemoziRemoteDataSource {
    override suspend fun getMemozi(): BaseResponse<ResponseGetMemoziDto> = memoziApi.getMemozi()
    override suspend fun postMemozi(requestPostMemoziDto: RequestPostMemoziDto): BaseResponse<Unit> =
        memoziApi.postMemozi(requestPostMemoziDto)
}

package com.memozi.memozi.source.remote

import com.memozi.memozi.model.remote.reponse.ResponseGetMemoziDto
import com.memozi.memozi.model.remote.request.RequestPostMemoziDto
import com.memozi.network.model.BaseResponse

interface MemoziRemoteDataSource {
    suspend fun getMemozi(): BaseResponse<ResponseGetMemoziDto>
    suspend fun postMemozi(requestPostMemoziDto: RequestPostMemoziDto): BaseResponse<Unit>
}

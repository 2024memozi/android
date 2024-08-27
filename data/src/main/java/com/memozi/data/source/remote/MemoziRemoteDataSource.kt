package com.memozi.data.source.remote

import com.memozi.data.model.remote.reponse.ResponseGetMemoziDto
import com.memozi.data.model.remote.request.RequestPostMemoziDto
import com.memozi.network.model.BaseResponse

interface MemoziRemoteDataSource {
    suspend fun getMemozi(): BaseResponse<ResponseGetMemoziDto>
    suspend fun postMemozi(requestPostMemoziDto: RequestPostMemoziDto): BaseResponse<Unit>
}

package com.memozi.memozi.api

import com.memozi.memozi.model.remote.reponse.ResponseGetMemoziDto
import com.memozi.memozi.model.remote.request.RequestPostMemoziDto
import com.memozi.network.model.BaseResponse

interface MemoziApi {
    suspend fun getMemozi(): BaseResponse<ResponseGetMemoziDto>
    suspend fun postMemozi(requestPostMemoziDto: RequestPostMemoziDto): BaseResponse<Unit>
}

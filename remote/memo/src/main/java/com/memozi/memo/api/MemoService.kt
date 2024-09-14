package com.memozi.memo.api

import com.memozi.memo.model.request.RequestMemo
import com.memozi.memo.model.response.ResponseMemo
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface MemoService {
    @POST("/memo/{categoryId}")
    suspend fun postMemo(
        @Path("categoryId") categoryId: Int,
        @Body requestMemo: RequestMemo
    ): ResponseMemo
}

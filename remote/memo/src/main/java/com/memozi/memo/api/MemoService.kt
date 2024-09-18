package com.memozi.memo.api

import com.memozi.memo.model.request.RequestMemo
import com.memozi.memo.model.response.ResponseMemo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MemoService {
    @POST("/memo/{categoryId}")
    suspend fun postMemo(
        @Path("categoryId") categoryId: Int,
        @Body requestMemo: RequestMemo
    ): ResponseMemo

    @GET("/memo/{categoryId}/{memoId}")
    suspend fun getMemo(
        @Path("categoryId") categoryId: Int,
        @Path("memoId") memoId: Int

    ): ResponseMemo

    @PUT("/{checkboxId}/check")
    suspend fun putCheck(
        @Path("checkboxId") checkboxId:Int
    )
}

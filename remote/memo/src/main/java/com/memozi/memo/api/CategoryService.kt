package com.memozi.memo.api

import com.memozi.memo.model.request.RequestPageable
import com.memozi.memo.model.response.ResponseCategory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryService {
    @GET("/category/{categoryId}")
    suspend fun getCategoryById(
        @Path("categoryId") categoryId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: List<String>
    ): ResponseCategory

    @GET("/category")
    suspend fun getAllCategories(
        @Query("pageable") pageable: RequestPageable
    ): List<ResponseCategory>

    @Multipart
    @POST("/category")
    suspend fun postCategory(
        @Query("name") name: String,
        @Query("defaultImageUrl") defaultImageUrl: String?,
        @Query("bgColorId") bgColorId: Int,
        @Query("txtColorId") txtColorId: Int
    ): Unit
}

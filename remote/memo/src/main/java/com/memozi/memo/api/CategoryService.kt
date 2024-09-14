package com.memozi.memo.api

import com.memozi.memo.model.request.RequestPageable
import com.memozi.memo.model.response.ResponseCategory
import com.memozi.memo.model.response.ResponseSearch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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
        @Part("name") name: RequestBody,
        @Part("defaultImageUrl") defaultImageUrl: RequestBody?,
        @Part("bgColorImageUrl") bgColorImageUrl: RequestBody?,
        @Part("txtColor") txtColor: RequestBody,
        @Part images: MultipartBody.Part?
    )

    @DELETE("/category/{categoryId}")
    suspend fun deleteCategory(
        @Path("categoryId") categoryId: Int
    )

    @Multipart
    @PUT("/category/{categoryId}")
    suspend fun updateCategory(
        @Path("categoryId") categoryId: Int,
        @Part("name") name: RequestBody,
        @Part("defaultImageUrl") defaultImageUrl: RequestBody?,
        @Part("bgColorImageUrl") bgColorImageUrl: RequestBody?,
        @Part("txtColor") txtColor: RequestBody,
        @Part images: MultipartBody.Part?
    ): ResponseCategory

    @GET("/category/search")
    suspend fun searchMemo(
        @Query("query") query: String
    ): ResponseSearch
}

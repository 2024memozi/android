package com.memozi.diary.api

import com.memozi.diary.model.response.ResponseDiary
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface DiaryService {
    @GET("/diary/{diaryId}")
    suspend fun getDiaryById(
        @Path("diaryId") diaryId: Int
    ): ResponseDiary

    @Multipart
    @PUT("/diary/{diaryId}")
    suspend fun putDiaryById(
        @Path("diaryId") diaryId: Int,
        @Part("title") title: String,
        @Part("content") content: String,
        @Part("location") location: String,
        @Part images: MultipartBody.Part?
    ): ResponseDiary

    @DELETE("/diary/{diaryId}")
    suspend fun delDiaryById(
        @Path("diaryId") diaryId: Int
    ): ResponseDiary

    @GET("/diary")
    suspend fun getDiary(): List<ResponseDiary>

    @Multipart
    @POST("/diary")
    suspend fun postDiary(
        @Part("title") title: RequestBody,
        @Part("content") content: RequestBody?,
        @Part("location") location: RequestBody?,
        @Part images: MultipartBody.Part?
    ): ResponseDiary
}

package com.memozi.diary.api

import com.memozi.diary.model.response.ResponseDiary
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DiaryService {
    @GET("/diary/{diaryId}")
    suspend fun getDiaryById(
        @Path("diaryId") diaryId: Int
    ): ResponseDiary

    @PUT("/diary/{diaryId}")
    suspend fun putDiaryById(
        @Path("diaryId") diaryId: Int,
        @Query("title") title: String,
        @Query("content") content: String,
        @Query("location") location: String
    ): ResponseDiary

    @DELETE("/diary/{diaryId}")
    suspend fun delDiaryById(
        @Path("diaryId") diaryId: Int
    ): ResponseDiary

    @GET("/diary")
    suspend fun getDiary(): List<ResponseDiary>
}

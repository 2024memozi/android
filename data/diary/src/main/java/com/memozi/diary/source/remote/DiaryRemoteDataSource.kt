package com.memozi.diary.source.remote

import com.memozi.diary.model.request.RequestDiary
import com.memozi.diary.model.response.ResponseDiary

interface DiaryRemoteDataSource {
    suspend fun getDiary(): List<ResponseDiary>

    suspend fun getDiaryByID(diaryId: Int): ResponseDiary

    suspend fun deleteDiary(diaryId: Int): ResponseDiary
    suspend fun postDiary(diary: RequestDiary): ResponseDiary
}

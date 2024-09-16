package com.memozi.diary.source.remote

import com.memozi.diary.model.request.RequesetDiary
import com.memozi.diary.model.response.ResponseDiary

interface DiaryRemoteDataSource {
    suspend fun getDiary(): ResponseDiary

    suspend fun getDiaryByID(diaryId: Int): ResponseDiary

    suspend fun deleteDiary(diaryId: Int): ResponseDiary
    suspend fun postDiary(diary: RequesetDiary): ResponseDiary
}

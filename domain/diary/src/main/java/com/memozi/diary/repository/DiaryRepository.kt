package com.memozi.diary.repository

import com.memozi.diary.model.Diary

interface DiaryRepository {
    suspend fun getDiary(): Result<List<Diary>>
    suspend fun getDiaryByID(diaryId: Int): Result<Diary>
    suspend fun deleteDiary(diaryId: Int): Result<Unit>
    suspend fun postDiary(content: String, image: String?, location: String?): Result<Diary>
}

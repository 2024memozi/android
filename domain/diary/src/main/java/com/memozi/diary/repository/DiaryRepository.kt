package com.memozi.diary.repository

import com.memozi.diary.model.Diary

interface DiaryRepository {
    suspend fun getDiary()
    suspend fun getDiaryByID(diaryId: Int)
    suspend fun deleteDiary(diaryId: Int)
    suspend fun postDiary(diary: Diary)
}

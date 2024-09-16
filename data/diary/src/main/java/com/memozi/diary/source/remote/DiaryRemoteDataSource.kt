package com.memozi.diary.source.remote

import com.memozi.diary.model.Diary

interface DiaryRemoteDataSource {
    suspend fun getDiary() {
        TODO("Not yet implemented")
    }

    suspend fun getDiaryByID(diaryId: Int) {
        TODO("Not yet implemented")
    }

    suspend fun deleteDiary(diaryId: Int) {
        TODO("Not yet implemented")
    }

    suspend fun postDiary(diary: Diary) {
        TODO("Not yet implemented")
    }
}

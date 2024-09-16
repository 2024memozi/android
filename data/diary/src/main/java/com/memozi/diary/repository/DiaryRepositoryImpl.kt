package com.memozi.diary.repository

import com.memozi.diary.model.Diary
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    remoteDataSource: DiaryRemoteDataSource
) : DiaryRepository {
    override suspend fun getDiary() {
        TODO("Not yet implemented")
    }

    override suspend fun getDiaryByID(diaryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiary(diaryId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun postDiary(diary: Diary) {
        TODO("Not yet implemented")
    }
}

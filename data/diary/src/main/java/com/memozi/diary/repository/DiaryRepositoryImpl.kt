package com.memozi.diary.repository

import com.memozi.diary.model.Diary
import com.memozi.diary.model.request.RequestDiary
import com.memozi.diary.model.response.toDomain
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val remoteDataSource: DiaryRemoteDataSource
) : DiaryRepository {

    override suspend fun getDiary() = runCatching {
        remoteDataSource.getDiary()
    }.mapCatching {
        it.map { it.toDomain() }
    }

    override suspend fun getDiaryByID(diaryId: Int) = runCatching {
        remoteDataSource.getDiaryByID(diaryId)
    }.mapCatching {
        it.toDomain()
    }

    override suspend fun deleteDiary(diaryId: Int): Result<Unit> = runCatching {
        remoteDataSource.deleteDiary(diaryId)
    }

    override suspend fun postDiary(
        content: String,
        image: String?,
        location: String?
    ): Result<Diary> = runCatching {
        remoteDataSource.postDiary(
            RequestDiary(title = "", content = content, location = location, images = emptyList())
        )
    }.mapCatching {
        it.toDomain()
    }
}

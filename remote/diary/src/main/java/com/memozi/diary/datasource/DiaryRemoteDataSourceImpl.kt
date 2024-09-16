package com.memozi.diary.datasource

import android.content.Context
import com.memozi.diary.api.DiaryService
import com.memozi.diary.model.request.RequestDiary
import com.memozi.diary.model.response.ResponseDiary
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DiaryRemoteDataSourceImpl @Inject constructor(
    private val diaryService: DiaryService,
    @ApplicationContext private val context: Context
) : DiaryRemoteDataSource {

    override suspend fun getDiary(): List<ResponseDiary> = diaryService.getDiary()

    override suspend fun getDiaryByID(diaryId: Int): ResponseDiary = diaryService.getDiaryById(diaryId)

    override suspend fun deleteDiary(diaryId: Int): ResponseDiary = diaryService.delDiaryById(diaryId)

    override suspend fun postDiary(diary: RequestDiary): ResponseDiary = diaryService.postDiary()
}

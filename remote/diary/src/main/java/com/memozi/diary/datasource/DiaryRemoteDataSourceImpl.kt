package com.memozi.diary.datasource

import android.content.Context
import com.memozi.diary.api.DiaryService
import com.memozi.diary.model.request.RequesetDiary
import com.memozi.diary.model.response.ResponseDiary
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DiaryRemoteDataSourceImpl @Inject constructor(
    private val diaryService: DiaryService,
    @ApplicationContext private val context: Context
) : DiaryRemoteDataSource {
    override suspend fun getDiary(): ResponseDiary {
        TODO("Not yet implemented")
    }

    override suspend fun getDiaryByID(diaryId: Int): ResponseDiary {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiary(diaryId: Int): ResponseDiary {
        TODO("Not yet implemented")
    }

    override suspend fun postDiary(diary: RequesetDiary): ResponseDiary {
        TODO("Not yet implemented")
    }
}

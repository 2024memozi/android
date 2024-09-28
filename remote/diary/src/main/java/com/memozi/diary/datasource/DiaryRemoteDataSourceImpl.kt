package com.memozi.diary.datasource

import android.content.Context
import androidx.core.net.toUri
import com.memozi.diary.FileConverter
import com.memozi.diary.api.DiaryService
import com.memozi.diary.model.response.ResponseDiary
import com.memozi.diary.source.remote.DiaryRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class DiaryRemoteDataSourceImpl @Inject constructor(
    private val diaryService: DiaryService,
    @ApplicationContext private val context: Context
) : DiaryRemoteDataSource {

    override suspend fun getDiary(): List<ResponseDiary> = diaryService.getDiary()

    override suspend fun getDiaryByID(diaryId: Int): ResponseDiary =
        diaryService.getDiaryById(diaryId)

    override suspend fun deleteDiary(diaryId: Int) =
        diaryService.delDiaryById(diaryId)

    override suspend fun postDiary(
        title: String,
        content: String,
        location: String?,
        image: String?
    ): ResponseDiary {
        image?.let {
            val imageUri = image.toUri()
            val file = FileConverter.uriToFile(context, imageUri)
            file?.let {
                val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
                val multipartBody =
                    MultipartBody.Part.createFormData("images", it.name, requestFile)

                // 서버에 파일을 업로드하는 로직 추가
                return diaryService.postDiary(
                    title = title.toRequestBody("text/plain".toMediaTypeOrNull()),
                    content = content.toRequestBody("text/plain".toMediaTypeOrNull()),
                    location = content.toRequestBody("text/plain".toMediaTypeOrNull()),
                    images = multipartBody
                )
            }
        }
        return diaryService.postDiary(
            title = title.toRequestBody("text/plain".toMediaTypeOrNull()),
            content = content.toRequestBody("text/plain".toMediaTypeOrNull()),
            location = content.toRequestBody("text/plain".toMediaTypeOrNull()),
            images = null
        )
    }
}

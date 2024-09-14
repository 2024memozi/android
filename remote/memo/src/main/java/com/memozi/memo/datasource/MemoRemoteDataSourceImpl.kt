package com.memozi.memo.datasource

import android.content.Context
import androidx.core.net.toUri
import com.memozi.memo.FileConverter
import com.memozi.memo.api.CategoryService
import com.memozi.memo.model.request.RequestPageable
import com.memozi.memo.model.response.ResponseCategory
import com.memozi.memo.source.remote.MemoRemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class MemoRemoteDataSourceImpl @Inject constructor(
    private val categoryService: CategoryService,
    @ApplicationContext private val context: Context
) : MemoRemoteDataSource {

    override suspend fun getCategory(
        page: Int,
        size: Int,
        sort: List<String>
    ): List<ResponseCategory> =
        categoryService.getAllCategories(pageable = RequestPageable(page, size, sort))

    override suspend fun postCategory(
        name: String,
        defaultImageUrl: String?,
        bgColorImageUrl: String?,
        txtColor: String,
        image: String?
    ) {
        image?.let {
            val imageUri = image.toUri()
            val file = FileConverter.uriToFile(context, imageUri)

            file?.let {
                val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
                val multipartBody =
                    MultipartBody.Part.createFormData("images", it.name, requestFile)

                // 서버에 파일을 업로드하는 로직 추가
                categoryService.postCategory(
                    name.toRequestBody("text/plain".toMediaTypeOrNull()),
                    defaultImageUrl?.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bgColorImageUrl?.toRequestBody("text/plain".toMediaTypeOrNull()),
                    txtColor.toRequestBody("text/plain".toMediaTypeOrNull()),
                    multipartBody
                )
            }
        }
        if (image == null) {
            categoryService.postCategory(
                name.toRequestBody("text/plain".toMediaTypeOrNull()),
                defaultImageUrl?.toRequestBody("text/plain".toMediaTypeOrNull()),
                bgColorImageUrl?.toRequestBody("text/plain".toMediaTypeOrNull()),
                txtColor.toRequestBody("text/plain".toMediaTypeOrNull()),
                null
            )
        }
    }
}
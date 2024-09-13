package com.memozi.memo.screen

import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : BaseViewModel<CategoryState, CategorySideEffect>(CategoryState()) {

    fun updateName(name: String) {
        intent { copy(name = name) }
    }

    fun updateImageUrl(imageUrl: String) {
        intent { copy(imageUrl = imageUrl, selectedColor = -1) }
    }

    fun updateTextColor() {
        intent { copy(textColor = if (selectedText == 0) "#000000" else "#ffffff") }
    }

    fun setSelectedColorIndex(colorIndex: Int) {
        intent { copy(selectedColor = colorIndex) }
    }

    fun setSelectedTextColorIndex(textColorIndex: Int) {
        intent { copy(selectedText = textColorIndex) }
        updateTextColor()
    }

    fun uriToFile(uri: Uri){

    }
    fun postCategory() {
        viewModelScope.launch {

            memoRepository.postCategory(
//                name = uiState.value.name,
//                defaultImageUrl = uiState.value.imageUrl,
//                bgColorId = uiState.value.bgColorId.(),
//                txtColorId = uiState.value.textColor.toColorInt()
//            ).onSuccess {
//                Log.d("post성공", "postCategory: $it")
//            }.onFailure {
//                when (it) {
//                    is ApiError -> Log.e("실패", it.message)
//                    else -> Log.e("실패", it.message.toString())
//                }
//            }
        }
    }

    fun navigateCategory(categoryId: Int) {
    }

    fun navigateCategoryAdd() {
    }
}

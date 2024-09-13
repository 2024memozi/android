package com.memozi.memo.screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.model.exception.ApiError
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    fun updateImageOpt(opt: Int) {
        intent { copy(selectImgOpt = opt) }
    }

    fun updateTextColor() {
        intent { copy(textColor = if (selectedText == 0) "#000000" else "#ffffff") }
    }

    fun setSelectedColorIndex(colorIndex: Int) {
        intent { copy(selectedColor = colorIndex, selectImgOpt = 2) }
    }

    fun setSelectedTextColorIndex(textColorIndex: Int) {
        intent { copy(selectedText = textColorIndex) }
        updateTextColor()
    }

    fun postCategory() {
        viewModelScope.launch {
            memoRepository.postCategory(
                name = uiState.value.name,
                defaultImageUrl = if (uiState.value.selectImgOpt == 1) uiState.value.imageUrl else null,
                bgColorImageUrl = if (uiState.value.selectedColor == 2) uiState.value.imageUrl else null,
                txtColor = uiState.value.textColor,
                image = if (uiState.value.selectImgOpt == 0) uiState.value.imageUrl else null
            ).onSuccess {
                Log.d("post성공", "uistate: ${uiState.value}")
            }.onFailure {
                when (it) {
                    is ApiError -> Log.e("실패", it.message)
                    else -> Log.e("실패", it.message.toString())
                }
            }
        }
    }

    fun navigateCategory(categoryId: Int) {
    }

    fun navigateCategoryAdd() {
    }
}

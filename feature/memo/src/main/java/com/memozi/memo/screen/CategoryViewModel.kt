package com.memozi.memo.screen

import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
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

    fun postCategory() {
        viewModelScope.launch {
//            memoRepository.postCategory(
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

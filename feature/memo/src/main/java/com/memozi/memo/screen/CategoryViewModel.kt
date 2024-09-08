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
        intent { copy(imageUrl = imageUrl) }
    }

    fun updateTextColor(textColor: Int) {
        intent { copy(textColor = textColor) }
    }

    fun updateBgColor(bgColorId: Int) {
        intent { copy(bgColorId = bgColorId) }
    }

    fun postCategory() {
        viewModelScope.launch {
            memoRepository.postCategory(
                name = uiState.value.name,
                defaultImageUrl = uiState.value.imageUrl,
                bgColorId = uiState.value.bgColorId,
                txtColorId = uiState.value.textColor
            )
//                .onSuccess {
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

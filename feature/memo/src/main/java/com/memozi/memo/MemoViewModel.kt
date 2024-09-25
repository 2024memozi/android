package com.memozi.memo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MemoState, MemoSideEffect>(MemoState()) {

    val categoryId =
        savedStateHandle.get<String>(com.memozi.memo.navigation.MemoRoute.CATEGORY_ID)?.toInt()

    fun getCategory() {
        viewModelScope.launch {
            memoRepository.getCategory(0, 10, emptyList())
                .onSuccess { categoryList -> intent { copy(categoryList = categoryList) } }
                .onFailure { Log.d("api 실패", "memo - getCategory: ${it.message}") }
        }
    }

    fun getCategory(categoryId: Int) {
        viewModelScope.launch {
            memoRepository.getCategory(categoryId, 0, 10, emptyList())
                .onSuccess { intent { copy(memoList = it.memo) } }
                .onFailure { Log.d("api 실패", "memo - getCategory id : ${it.message}") }
        }
    }

    fun setMemoEmpty() {
        intent { copy(memoList = emptyList()) }
    }

    fun navigateSetting() {
        postSideEffect(MemoSideEffect.NavigateToSettings)
    }

    fun navigateMemo(memoId: Int) {
        postSideEffect(MemoSideEffect.NavigateToMemo(memoId))
    }

    fun navigateMemoAdd() {
        postSideEffect(MemoSideEffect.NavigateMemoAdd)
    }

    fun navigateCategory(categoryId: Int) {
        postSideEffect(MemoSideEffect.NavigateToCategory(categoryId))
    }

    fun navigateCategoryAdd() {
        postSideEffect(MemoSideEffect.NavigateToCategoryAdd)
    }

    fun navigateSearch() {
        postSideEffect(MemoSideEffect.NavigateSearch)
    }

    fun putCheck(checkboxId: Int) {
        viewModelScope.launch {
            memoRepository.putCheckBox(checkboxId).onSuccess {
            }
        }
    }
}

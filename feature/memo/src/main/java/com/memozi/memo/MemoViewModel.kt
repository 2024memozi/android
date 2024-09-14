package com.memozi.memo

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : BaseViewModel<MemoState, MemoSideEffect>(MemoState()) {

    fun getCategory() {
        viewModelScope.launch {
            memoRepository.getCategory(0, 10, emptyList())
                .onSuccess { categoryList ->
                    intent { copy(categoryList = categoryList) }
                }
                .onFailure {
                    Log.d("api 실패", "memo - getCategory: ${it.message}")
                }
        }
    }

    fun setMemo(selectCategory: Int) {
        if (selectCategory >= uiState.value.categoryList.size) {
            intent { copy(memoList = emptyList()) }
        } else if (uiState.value.categoryList.isNotEmpty()) intent { copy(memoList = uiState.value.categoryList[selectCategory].memo) }
    }

    fun navigateSetting() {
        postSideEffect(MemoSideEffect.NavigateToSettings)
    }

    fun navigateMemo(memoId: Int) {
        postSideEffect(MemoSideEffect.NavigateToMemo(memoId))
    }

    fun navigateCategory(categoryId: Int) {
        postSideEffect(MemoSideEffect.NavigateToCategory(categoryId))
    }

    fun navigateCategoryAdd() {
        postSideEffect(MemoSideEffect.NavigateToCategoryAdd)
    }

    fun navigateSearch(){
        postSideEffect(MemoSideEffect.NavigateSearch)
    }
}

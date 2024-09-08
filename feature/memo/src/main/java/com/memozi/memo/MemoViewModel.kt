package com.memozi.memo

import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor() : BaseViewModel<MemoState, MemoSideEffect>(MemoState()) {

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
}

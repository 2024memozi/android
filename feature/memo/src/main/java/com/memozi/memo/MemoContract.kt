package com.memozi.memo

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class MemoState(
    val nickname: String = ""
) : UiState

sealed interface MemoSideEffect : SideEffect {
    data class NavigateToMemo(val memoId: Int) : MemoSideEffect
    data class NavigateToCategory(val categoryId: Int) : MemoSideEffect
    data object NavigateToCategoryAdd : MemoSideEffect
    data object NavigateToSettings : MemoSideEffect
}

package com.memozi.memo

import com.memozi.memo.model.Category
import com.memozi.memo.model.Memo
import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState
import kotlinx.collections.immutable.toImmutableList

data class MemoState(
    val nickname: String = "",
    val memoList: List<Memo> = emptyList<Memo>().toImmutableList(),
    val categoryList: List<Category> = emptyList<Category>().toImmutableList()
) : UiState

sealed interface MemoSideEffect : SideEffect {
    data class NavigateToMemo(val memoId: Int) : MemoSideEffect
    data class NavigateToCategory(val categoryId: Int) : MemoSideEffect
    data object NavigateToCategoryAdd : MemoSideEffect
    data object NavigateToSettings : MemoSideEffect
    data object NavigateSearch : MemoSideEffect
    data object NavigateMemoAdd : MemoSideEffect
}

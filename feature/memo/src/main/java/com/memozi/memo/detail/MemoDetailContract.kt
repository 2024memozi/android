package com.memozi.memo.detail

import com.memozi.memo.model.Category
import com.memozi.memo.model.Memo
import com.memozi.memo.model.SearchResult
import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class MemoDetailState(
    val result: List<SearchResult> = emptyList(),
    val query: String = "",
    val categoryName: String = "카테고리",
    val editMode: Boolean = false,
    val categoryList: List<Category> = emptyList(),
    val memo: Memo = Memo(memoId = 0, "", "", "", emptyList(), "", "")

) : UiState

sealed interface MemoDetailSideEffect : SideEffect {
    data object NavigateMemo : MemoDetailSideEffect
}

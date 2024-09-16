package com.memozi.memo.detail

import com.memozi.memo.model.SearchResult
import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class MemoDetailState(
    val result: List<SearchResult> = emptyList(),
    val query: String = ""
) : UiState

sealed interface MemoDetailSideEffect : SideEffect {
    data object NavigateMemo : MemoDetailSideEffect
}

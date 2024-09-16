package com.memozi.memo.search

import com.memozi.memo.model.SearchResult
import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class MemoSearchState(
    var result: List<SearchResult> = emptyList(),
    val query: String = ""
) : UiState

sealed interface MemoSearchEffect : SideEffect

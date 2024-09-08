package com.memozi.memo.screen

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class CategoryState(
    val name: String = "",
    val imageUrl: String = "",
    val textColor: Int=1,
    val bgColorId: Int=0,
    ) : UiState

sealed interface CategorySideEffect : SideEffect {
    data class NavigateToMemo(val memoId: Int) : CategorySideEffect
    data class NavigateToCategory(val categoryId: Int) : CategorySideEffect
    data object NavigateToCategoryAdd : CategorySideEffect
    data object NavigateToSettings : CategorySideEffect
}

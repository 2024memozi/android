package com.memozi.memo.screen

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class CategoryState(
    val name: String = "",
    val imageUrl: String = "https://github.com/user-attachments/assets/6443c2d2-c1f9-43a5-9d52-840a6b765fcb",
    val textColor: String = "#000000",
    val bgColorId: Int = 0

) : UiState

sealed interface CategorySideEffect : SideEffect {
    data class NavigateToMemo(val memoId: Int) : CategorySideEffect
    data class NavigateToCategory(val categoryId: Int) : CategorySideEffect
    data object NavigateToCategoryAdd : CategorySideEffect
    data object NavigateToSettings : CategorySideEffect
}

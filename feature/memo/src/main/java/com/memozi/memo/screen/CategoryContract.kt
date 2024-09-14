package com.memozi.memo.screen

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class CategoryState(
    val name: String = "",
    val imageUrl: String = "https://github.com/user-attachments/assets/f463d586-5cac-4a4f-852c-981688b31279",
    val textColor: String = "#000000",
    val selectedColor: Int = -1,
    val selectedText: Int = 0,
    val defaultImgId: Int = 2,
    val selectImgOpt: Int = 1, // 0 - url 사진 , 1- 기본 , 2- color

    val btnEnable: Boolean = false

) : UiState

sealed interface CategorySideEffect : SideEffect {
    data object NavigateToMemo : CategorySideEffect
    data class NavigateToCategory(val categoryId: Int) : CategorySideEffect
    data object NavigateToCategoryAdd : CategorySideEffect
    data object NavigateToSettings : CategorySideEffect
}

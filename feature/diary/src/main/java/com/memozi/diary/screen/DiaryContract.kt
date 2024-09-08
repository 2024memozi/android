package com.memozi.diary.screen

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class DiaryState(
    var name: String = ""
): UiState

sealed interface DiaryEffect: SideEffect {
    data object navigateMemo: DiaryEffect
    data object navigateSetting: DiaryEffect
}
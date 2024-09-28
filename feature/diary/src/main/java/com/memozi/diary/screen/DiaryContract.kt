package com.memozi.diary.screen

import com.memozi.diary.model.Diary
import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class DiaryState(
    var name: String = "",
    var location: String? = null,
    var content: String = "",
    var image: String? = null,
    var diaryList: List<Diary> = emptyList()
) : UiState

sealed interface DiaryEffect : SideEffect {
    data object navigateMemo : DiaryEffect
    data object navigateSetting : DiaryEffect
}

package com.memozi.setting

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class SettingState(
    val email: String = "",
    val name: String = ""
) : UiState

sealed interface SettingSideEffect : SideEffect {
    data object Info : SettingSideEffect
    data object Delete : SettingSideEffect
    data object Logout : SettingSideEffect
}

package com.memozi.onboarding

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class OnboardingState(
    val name: String = "",
    val selectedAmPm: String = "오전",
    val selectedHour: Int = 1,
    val selectedMinute: Int = 1,
    val isNotification: Boolean = false
) : UiState

sealed interface OnboardingSideEffect : SideEffect {
    data object NavigateToHome : OnboardingSideEffect
}

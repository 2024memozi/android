package com.memozi.onboarding

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class OnboardingState(
    val nickname: String = ""
) : UiState

sealed interface OnboardingSideEffect : SideEffect {
    data object NavigateToHome : OnboardingSideEffect
}

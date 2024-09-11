package com.memozi.onboarding

import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() :
    BaseViewModel<OnboardingState, OnboardingSideEffect>(OnboardingState()) {

    fun setAmPm(selectedAmPm: String) {
        intent { copy(selectedAmPm = selectedAmPm) }
    }

    fun setHour(selectedHour: Int) {
        intent { copy(selectedHour = selectedHour) }
    }

    fun setMinute(selectedMinute: Int) {
        intent { copy(selectedMinute = selectedMinute) }
    }

    fun navigateHome() {
        postSideEffect(OnboardingSideEffect.NavigateToHome)
    }
}

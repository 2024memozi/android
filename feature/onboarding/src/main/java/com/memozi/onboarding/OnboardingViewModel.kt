package com.memozi.onboarding

import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() :
    BaseViewModel<OnboardingState, OnboardingSideEffect>(OnboardingState()) {

    fun navigateHome() {
        postSideEffect(OnboardingSideEffect.NavigateToHome)
    }
}

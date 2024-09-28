package com.memozi.onboarding

import androidx.lifecycle.viewModelScope
import com.memozi.auth.repository.AuthRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authRepository: AuthRepository
) :
    BaseViewModel<OnboardingState, OnboardingSideEffect>(OnboardingState()) {

    init {
        viewModelScope.launch {
            authRepository.getUserData().onSuccess { intent { copy(name = it.nickname) } }
        }
    }

    fun setAmPm(selectedAmPm: String) {
        intent { copy(selectedAmPm = selectedAmPm) }
    }

    fun setHour(selectedHour: Int) {
        intent { copy(selectedHour = selectedHour) }
    }

    fun setMinute(selectedMinute: Int) {
        intent { copy(selectedMinute = selectedMinute) }
    }

    fun setIsNotification(isNotification: Boolean) {
        intent { copy(isNotification = isNotification) }
    }

    fun navigateHome() {
        postSideEffect(OnboardingSideEffect.NavigateToHome)
    }
}

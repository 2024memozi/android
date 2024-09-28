package com.memozi.setting

import androidx.lifecycle.viewModelScope
import com.memozi.auth.repository.AuthRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewmodel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<SettingState, SettingSideEffect>(SettingState()) {

    fun navigateDelete() {
        postSideEffect(SettingSideEffect.Delete)
    }

    fun logOut() {
        viewModelScope.launch {
            authRepository.logout().onSuccess {
                postSideEffect(SettingSideEffect.Logout)
            }
        }
    }
}

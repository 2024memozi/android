package com.memozi.setting

import android.util.Log
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

    init {
        viewModelScope.launch {
            authRepository.getUserData().onSuccess {
                intent { copy(email = it.email, name = it.nickname) }
            }
        }
    }

    fun navigateDelete() {
        viewModelScope.launch {
            authRepository.delete().onSuccess {
                postSideEffect(SettingSideEffect.Delete)
            }.onFailure {
                Log.d("delete fail", "navigateDelete: ${it.message}")
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            authRepository.logout().onSuccess {
                postSideEffect(SettingSideEffect.Logout)
            }
        }
    }
}

package com.memozi.login

import com.memozi.ui.base.SideEffect
import com.memozi.ui.base.UiState

data class LoginState(
    var splash: Boolean = true
) : UiState

sealed interface LoginSideEffect : SideEffect {
    data object StartLogin : LoginSideEffect
    data object LoginSuccess : LoginSideEffect
    data object LoginToSignUp : LoginSideEffect
    data class LoginError(val errorMessage: String) : LoginSideEffect
}

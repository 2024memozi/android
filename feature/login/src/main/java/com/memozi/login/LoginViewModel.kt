package com.memozi.login

import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    fun startKakaoLogin() {
        postSideEffect(LoginSideEffect.StartLogin)
    }
}

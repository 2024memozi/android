package com.memozi.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.auth.repository.AuthRepository
import com.memozi.model.AuthEntity
import com.memozi.model.UserEntity
import com.memozi.model.exception.ApiError
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {

    fun startKakaoLogin() {
        postSideEffect(LoginSideEffect.StartLogin)
    }

    fun getUser() {
        viewModelScope.launch {
            authRepository.getLocalData().onSuccess {
//                if (it.accessToken.isNotEmpty()) postSideEffect(LoginSideEffect.LoginSuccess)
            }
        }
    }

    fun signIn(socialToken: String) {
        viewModelScope.launch {
            authRepository.signIn(socialToken)
                .onSuccess {
                    authRepository.saveLocalData(AuthEntity(it.accessToken, it.refreshToken))
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }.onFailure {
                    when (it) {
                        is ApiError -> Log.e("실패", it.message)
                        else -> Log.e("실패", it.message.toString())
                    }
                    postSideEffect(LoginSideEffect.LoginError(errorMessage = it.message.toString()))
                }
        }
    }

    fun setUser(user: UserEntity) {
        viewModelScope.launch {
            authRepository.saveUserData(user).onFailure {
                when (it) {
                    is ApiError -> Log.e("실패", it.message)
                    else -> Log.e("실패", it.message.toString())
                }
            }
        }
    }
}

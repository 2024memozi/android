package com.memozi.diary.screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.auth.repository.AuthRepository
import com.memozi.diary.repository.DiaryRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val diaryRepository: DiaryRepository
) : BaseViewModel<DiaryState, DiaryEffect>(DiaryState()) {

    fun getName() {
        viewModelScope.launch {
            authRepository.getUserData().onSuccess {
                intent { copy(name = it.nickname) }
            }
        }
    }

    fun getDiary() {
        viewModelScope.launch {
            diaryRepository.getDiary().onSuccess {
                intent { copy(diaryList = it) }
            }.onFailure {
                Log.d("실패", " ${it.message}")
            }
        }
    }

    fun postDiary(content: String, image: String?, location: String?) {
        viewModelScope.launch {
            diaryRepository.postDiary(content, uiState.value.image, location).onSuccess {
                getDiary()
            }
        }
    }

    fun delteDiary(diaryId: Int) {
        viewModelScope.launch {
            diaryRepository.deleteDiary(diaryId).onSuccess {
                getDiary()
            }
        }
    }

    fun updateUri(uri: String) {
        intent { copy(image = uri) }
    }

    fun navigateToMemo() {
        postSideEffect(DiaryEffect.navigateMemo)
    }

    fun navigateToSetting() {
        postSideEffect(DiaryEffect.navigateSetting)
    }
}

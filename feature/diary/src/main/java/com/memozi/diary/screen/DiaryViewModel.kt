
package com.memozi.diary.screen

import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor() : BaseViewModel<DiaryState, DiaryEffect>(DiaryState()) {

    fun getName(name: String) {
        intent { copy(name = name) }
    }

    fun navigateToMemo() {
        postSideEffect(DiaryEffect.navigateMemo)
    }

    fun navigateToSetting() {
        postSideEffect(DiaryEffect.navigateSetting)
    }
}

package com.memozi.memo.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.memozi.memo.model.CheckBox
import com.memozi.memo.navigation.MemoRoute
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoDetailViewModel @Inject constructor(
    private val memoRepository: MemoRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<MemoDetailState, MemoDetailSideEffect>(MemoDetailState()) {

    val categoryId =
        savedStateHandle.get<String>(MemoRoute.CATEGORY_ID)?.toInt()

    val memoId =
        savedStateHandle.get<String>(MemoRoute.MEMO_ID)?.toInt()

    fun getMemo() {
        viewModelScope.launch {
            categoryId?.let {
                memoId?.let {
                    memoRepository.getMemo(categoryId, memoId).onSuccess {
                        intent { copy(memo = it, editMode = true) }
                    }
                }?.onSuccess {
                    intent { copy(memo = it, beforeCheckList = it.checkBoxes) }
                }
            }
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            memoRepository.getCategory(0, 10, emptyList())
                .onSuccess { categoryList -> intent { copy(categoryList = categoryList) } }
                .onFailure { Log.d("api 실패", "memo detail - getCategory: ${it.message}") }
        }
    }

    fun postmemo() {
        viewModelScope.launch {
            categoryId?.let {
                memoRepository.postMemo(
                    categoryId,
                    uiState.value.memo.title,
                    uiState.value.memo.content,
                    uiState.value.memo.checkBoxes
                ).onSuccess {
                    postSideEffect(MemoDetailSideEffect.NavigateMemo)
                }
            }
        }
    }

    fun putCheckBox() {
        viewModelScope.launch {
            val updatedCheckBoxes = uiState.value.beforeCheckList.filter { newCheckBox ->
                uiState.value.memo.checkBoxes.any { oldCheckBox ->
                    oldCheckBox.id == newCheckBox.id && oldCheckBox.checked != newCheckBox.checked
                }
            }
            updatedCheckBoxes.forEach {
                memoRepository.putCheckBox(it.id).onSuccess {
                }.onFailure {
                    Log.d("putcheckbox 실패", "putCheckBox: ${it.message}")
                }
            }
        }
    }

    fun updateChecked(checkboxIndex: Int) {
        val updatedCheckBoxes = uiState.value.memo.checkBoxes.mapIndexed { index, checkBox ->
            if (index == checkboxIndex) {
                checkBox.copy(checked = !checkBox.checked)
            } else {
                checkBox
            }
        }
        intent { copy(memo = memo.copy(checkBoxes = updatedCheckBoxes)) }
    }

    fun updateCheckContent(checkboxIndex: Int, content: String) {
        val updatedCheckBoxes = uiState.value.memo.checkBoxes.mapIndexed { index, checkBox ->
            if (index == checkboxIndex) {
                checkBox.copy(content = content)
            } else {
                checkBox
            }
        }
        intent { copy(memo = memo.copy(checkBoxes = updatedCheckBoxes)) }
    }

    fun putmemo() {
        viewModelScope.launch {
            categoryId?.let {
                memoId?.let {
                    memoRepository.putMemo(
                        categoryId,
                        memoId,
                        uiState.value.memo.title,
                        uiState.value.memo.content,
                        uiState.value.memo.checkBoxes
                    ).onSuccess {
                        postSideEffect(MemoDetailSideEffect.NavigateMemo)
                        putCheckBox()
                    }
                }
            }
        }
    }

    fun updateTitle(title: String) {
        intent { copy(memo = memo.copy(title = title)) }
    }

    fun updateContent(content: String) {
        intent { copy(memo = memo.copy(content = content)) }
    }

    fun newCheckBox() {
        intent {
            copy(
                memo = memo.copy(
                    checkBoxes = uiState.value.memo.checkBoxes + CheckBox(
                        0,
                        "",
                        false
                    )
                )
            )
        }
    }

    fun deleteMemo() {
        viewModelScope.launch {
            categoryId?.let {
                memoId?.let {
                    memoRepository.deleteMemo(categoryId, memoId).onSuccess {
                        postSideEffect(MemoDetailSideEffect.NavigateMemo)
                    }.onFailure { Log.d("deleteMemo error", "deleteMemo: ${it.message}") }
                }
            }
        }
    }
}

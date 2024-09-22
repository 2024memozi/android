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
                    intent { copy(memo = it) }
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

    fun putmemo(
        title: String,
        content: String,
        checkBoxs: List<CheckBox>
    ) {
        viewModelScope.launch {
            categoryId?.let {
                memoRepository.putMemo(categoryId, title, content, checkBoxs).onSuccess {
                    postSideEffect(MemoDetailSideEffect.NavigateMemo)
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

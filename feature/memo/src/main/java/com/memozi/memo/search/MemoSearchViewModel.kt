package com.memozi.memo.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoSearchViewModel
    @Inject
    constructor(
        private val memoRepository: MemoRepository,
    ) : BaseViewModel<MemoSearchState, MemoSearchEffect>(MemoSearchState()) {
        fun getResult(query: String) {
            viewModelScope.launch {
                memoRepository
                    .getCategorySearch(query)
                    .onSuccess { resultList ->
                        if (resultList.isEmpty() || query.isEmpty()) {
                            intent { copy(result = emptyList()) }
                        } else {
                            intent { copy(result = resultList) }
                        }
                    }.onFailure {
                        Log.d("getResult 실패", "getResult: ${it.message}")
                    }
            }
        }

        fun navigateMemo() {
            postSideEffect(MemoSearchEffect.NavigateToMemo)
        }
    }

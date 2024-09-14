package com.memozi.memo.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.memozi.memo.repository.MemoRepository
import com.memozi.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoSearchViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : BaseViewModel<MemoSearchState, MemoSearchEffect>(MemoSearchState()) {

    suspend fun getResult(){
        viewModelScope.launch {
            memoRepository.getCategorySearch(uiState.value.query).onSuccess {
                Log.d("getResult", "getResult: $it")
            }.onFailure {
                Log.d("getResult 실패", "getResult: ${it.message}")
            }
        }
        
    }
}
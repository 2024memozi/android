package com.memozi.memo.model

data class SearchResult(
    val name: String,
    val count: Int,
    val memos: List<Memo>
)

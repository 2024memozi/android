package com.memozi.memo.model

data class Category(
    val name: String,
    val representImage: String,
    val bgColor: String,
    val txtColor: String,
    val memos: List<Memo>,
    val hasNext: Boolean
)

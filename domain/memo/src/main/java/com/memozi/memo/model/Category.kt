package com.memozi.memo.model

data class Category(
    val name: String,
    val representImage: String,
    val txtColor: String,
    val memo: List<Memo>
)

package com.memozi.memo.model

data class Category(
    val categoryId:Int,
    val name: String,
    val representImage: String,
    val txtColor: String,
    val memo: List<Memo>
)

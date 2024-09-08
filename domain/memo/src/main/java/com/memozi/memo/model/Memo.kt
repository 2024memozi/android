package com.memozi.memo.model

data class Memo(
    val title: String,
    val content: String,
    val dayOfWeek: String,
    val checkBoxes: List<CheckBox>,
    val createdAt: String,
    val updatedAt: String
)
package com.memozi.memo.model

data class Memo(
    val memoId: Int,
    val title: String,
    val content: String,
    val dayOfWeek: String,
    val checkBoxes: List<CheckBox>,
    val createdAt: String,
    val updatedAt: String
)

fun MemoDummy() =
    Memo(
        0, "title", "내용", "",
        checkBoxes = listOf(
            CheckBox(0, "할일 체크리스트", false),
            CheckBox(0, "할일 체크리스트", false),
        ), "", ""
    )

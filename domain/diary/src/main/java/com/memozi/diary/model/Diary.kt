package com.memozi.diary.model

data class Diary(
    val diaryId: Int,
    val title: String,
    val content: String,
    val images: List<String>,
    val location: String?,
    val dayOfWeek: String,
    val createdAt: String
)

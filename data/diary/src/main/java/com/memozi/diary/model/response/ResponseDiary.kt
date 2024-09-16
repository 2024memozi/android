package com.memozi.diary.model.response

import com.memozi.diary.model.Diary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDiary(
    @SerialName("diaryId")
    val diaryId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("dayOfWeek")
    val dayOfWeek: String,
    @SerialName("createdAt")
    val createdAt: String
)

fun ResponseDiary.toDomain() = Diary(
    title = title,
    content = content,
    diaryId = diaryId,
    createdAt = createdAt,
    dayOfWeek = dayOfWeek,
    images = emptyList(),
    location = null
)

package com.memozi.diary.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequesetDiary(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("location")
    val location: String,
    @SerialName("diaryId")
    val diaryId: Int
)

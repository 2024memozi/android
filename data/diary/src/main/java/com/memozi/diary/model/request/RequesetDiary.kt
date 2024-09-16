package com.memozi.diary.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDiary(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("location")
    val location: String?,
    @SerialName("images")
    val images: List<String>
)

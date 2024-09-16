package com.memozi.memo.model.response

import com.memozi.memo.model.Memo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMemo(
    @SerialName("memoId")
    val memoId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("dayOfWeek")
    val dayOfWeek: String,
    @SerialName("checkBoxes")
    val checkBoxes: List<ResponseCheckBox>,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String
)

fun ResponseMemo.toDomain() = Memo(
    memoId = memoId,
    title = title,
    content = content,
    dayOfWeek = dayOfWeek,
    checkBoxes = checkBoxes.map { it.toDomain() },
    createdAt = createdAt,
    updatedAt = updatedAt
)

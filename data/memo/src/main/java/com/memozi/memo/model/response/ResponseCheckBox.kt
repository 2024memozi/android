package com.memozi.memo.model.response

import com.memozi.memo.model.CheckBox
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCheckBox(
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
    @SerialName("checked")
    val checked: Boolean
)

fun ResponseCheckBox.toDomain() = CheckBox(
    id = id,
    content = content,
    checked = checked
)
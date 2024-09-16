package com.memozi.memo.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestMemo(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("checkBoxes")
    val checkBoxes: List<RequestCheckBox>
)

@Serializable
data class RequestCheckBox(
    @SerialName("id")
    val id: Int,
    @SerialName("content")
    val content: String,
    @SerialName("checked")
    val checked: Boolean
)

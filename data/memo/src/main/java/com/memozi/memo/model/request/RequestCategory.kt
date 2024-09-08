package com.memozi.memo.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategory(
    @SerialName("name")
    val name: String,
    @SerialName("defaultImageUrl")
    val defaultImageUrl: String,
    @SerialName("bgColorId")
    val bgColorId: Int,
    @SerialName("txtColorId")
    val txtColorId: Int
)

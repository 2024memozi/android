package com.memozi.memo.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPageable(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("size")
    val size: Int = 1,
    @SerialName("sort")
    val sort: List<String> = listOf("string")
)


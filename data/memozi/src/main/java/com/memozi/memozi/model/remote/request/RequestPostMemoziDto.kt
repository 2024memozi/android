package com.memozi.memozi.model.remote.request

import com.memozi.memozi.domain.model.Memozi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestPostMemoziDto(
    @SerialName("test")
    val test: String
)

fun Memozi.toData() = RequestPostMemoziDto(
    test = this.test
)

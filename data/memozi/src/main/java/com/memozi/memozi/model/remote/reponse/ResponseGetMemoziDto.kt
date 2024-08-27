package com.memozi.memozi.model.remote.reponse

import com.memozi.memozi.domain.model.Memozi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseGetMemoziDto(
    @SerialName("test")
    val test: String
)

fun ResponseGetMemoziDto.toDomain() = Memozi(
    test = this.test
)

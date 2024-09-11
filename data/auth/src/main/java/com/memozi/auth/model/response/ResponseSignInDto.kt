package com.memozi.auth.model.response

import com.memozi.model.AuthEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignInDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)

fun ResponseSignInDto.toModel() = AuthEntity(
    accessToken,
    refreshToken
)

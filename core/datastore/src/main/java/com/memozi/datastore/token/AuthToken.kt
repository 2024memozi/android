package com.memozi.datastore.token

import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    val accessToken: String = "",
    val refreshToken: String = "",
    val kakaoToken: String = ""
)

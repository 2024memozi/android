package com.memozi.model

data class AuthEntity(
    val accessToken: String,
    val refreshToken: String,
    val kakaoToken: String
)

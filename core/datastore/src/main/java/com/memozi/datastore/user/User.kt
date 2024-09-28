package com.memozi.datastore.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String = "",
    val nickname: String = ""
)

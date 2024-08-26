package com.memozi.datastore

import kotlinx.serialization.Serializable

@Serializable
data class MemoziLocalData(
    val a: Int = 0,
    val b: String = "",
)

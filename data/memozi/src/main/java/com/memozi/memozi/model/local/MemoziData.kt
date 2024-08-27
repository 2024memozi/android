package com.memozi.memozi.model.local

import com.memozi.model.CoreData

data class MemoziData(
    val a: Int,
    val b: String
)

fun MemoziData.toCore() = CoreData(
    a = this.a,
    b = this.b
)

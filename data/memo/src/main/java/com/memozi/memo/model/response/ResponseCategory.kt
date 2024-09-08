package com.memozi.memo.model.response

import com.memozi.memo.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseCategory(
    @SerialName("name")
    val name: String,
    @SerialName("representImage")
    val representImage: String,
    @SerialName("bgColor")
    val bgColor: String,
    @SerialName("txtColor")
    val txtColor: String,
    @SerialName("memos")
    val memos: List<ResponseMemo>,
    @SerialName("hasNext")
    val hasNext: Boolean
)

fun ResponseCategory.toDomain() = Category(
    name = name,
    representImage = representImage,
    bgColor = bgColor,
    txtColor = txtColor,
    memos = memos.map { it.toDomain() },
    hasNext = hasNext
)

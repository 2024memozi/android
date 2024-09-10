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
    @SerialName("txtColor")
    val txtColor: String,
    @SerialName("memo")
    val memo: List<ResponseMemo>
)

fun ResponseCategory.toDomain() = Category(
    name = name,
    representImage = representImage,
    txtColor = txtColor,
    memo = memo.map { it.toDomain() }
)

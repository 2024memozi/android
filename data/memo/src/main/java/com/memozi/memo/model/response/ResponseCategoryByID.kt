package com.memozi.memo.model.response

import com.memozi.memo.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCategoryByID(
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("representImage")
    val representImage: String,
    @SerialName("txtColor")
    val txtColor: String,
    @SerialName("memos")
    val memos: List<ResponseMemo>,
    @SerialName("hasNext")
    val hasNext: Boolean
)

fun ResponseCategoryByID.toDomain() = Category(
    categoryId = categoryId,
    name = name,
    representImage = representImage,
    txtColor = txtColor,
    memo = memos.map { it.toDomain() }
)

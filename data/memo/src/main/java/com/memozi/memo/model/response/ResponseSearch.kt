package com.memozi.memo.model.response

import com.memozi.memo.model.SearchResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSearch(
    @SerialName("name")
    val name: String,
    @SerialName("count")
    val count: Int,
    @SerialName("memos")
    val memos: List<ResponseMemo>,
)

fun ResponseSearch.toDomain() = SearchResult(
    name = name,
    count = count,
    memos = memos.map { it.toDomain() }
)


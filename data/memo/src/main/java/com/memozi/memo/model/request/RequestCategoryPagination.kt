package com.memozi.memo.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestCategoryPagination(
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("pageable")
    val pageable: RequestPageable
)


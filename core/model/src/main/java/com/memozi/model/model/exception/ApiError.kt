package com.memozi.model.exception

data class ApiError(
    override val message: String,
) : Exception()

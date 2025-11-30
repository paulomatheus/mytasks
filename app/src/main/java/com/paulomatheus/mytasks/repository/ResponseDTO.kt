package com.paulomatheus.mytasks.repository

data class ResponseDTO<T>(
    val value: T? = null,
    val error: Boolean = false
)

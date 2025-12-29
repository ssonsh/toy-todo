package com.todo.common.error

data class ApiErrorResponse (
    val code: String,
    val message: String,
    val type: String,
    val metadata: Map<String, Any?> = emptyMap(),
)
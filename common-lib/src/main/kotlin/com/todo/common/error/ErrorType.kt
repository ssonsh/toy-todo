package com.todo.common.error

enum class ErrorType(
    val httpStatus: Int,
    val code: String,
    val defaultMessage: String,
) {
    INVALID_REQUEST(400, "400.0001", "요청이 올바르지 않습니다."),
    NOT_FOUND(404, "404.0001", "리소스를 찾을 수 없습니다."),
    CONFLICT(409, "409.0001", "이미 존재합니다."),
    INTERNAL_ERROR(500, "500.0000", "서버 오류입니다."),
}
package com.todo.common.error

open class ApplicationException(
    val errorType: ErrorType,
    override val message: String = errorType.defaultMessage,
    val metadata: Map<String, Any?> = emptyMap(),
    val loggingRequired: Boolean = true,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

class InvalidRequestException(
    message: String,
    metadata: Map<String, Any?> = emptyMap(),
) : ApplicationException(ErrorType.INVALID_REQUEST, message, metadata, loggingRequired = false)

class NotFoundException(
    message: String,
    metadata: Map<String, Any?> = emptyMap(),
) : ApplicationException(ErrorType.NOT_FOUND, message, metadata, loggingRequired = false)

class ConflictException(
    message: String,
    metadata: Map<String, Any?> = emptyMap(),
) : ApplicationException(ErrorType.CONFLICT, message, metadata, loggingRequired = false)
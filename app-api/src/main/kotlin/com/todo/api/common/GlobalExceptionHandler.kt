package com.todo.api.common

import com.todo.common.error.ApiErrorResponse
import com.todo.common.error.ApplicationException
import com.todo.common.error.ErrorType
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(
        e: ApplicationException,
        request: HttpServletRequest,
    ): ResponseEntity<ApiErrorResponse> {
        if (e.loggingRequired) {
            log.warn("ApplicationException: uri={}, type={}, code={}, message={}, meta={}",
                request.requestURI, e.errorType.name, e.errorType.code, e.message, e.metadata, e)
        } else {
            log.info("ApplicationException: uri={}, type={}, code={}, message={}, meta={}",
                request.requestURI, e.errorType.name, e.errorType.code, e.message, e.metadata)
        }

        val body = ApiErrorResponse(
            code = e.errorType.code,
            message = e.message,
            type = e.errorType.name,
            metadata = e.metadata,
        )
        return ResponseEntity.status(e.errorType.httpStatus).body(body)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class, BindException::class)
    fun handleValidation(e: Exception): ResponseEntity<ApiErrorResponse> {
        val msg = when (e) {
            is MethodArgumentNotValidException ->
                e.bindingResult.fieldErrors.joinToString("\n") { "${it.field}: ${it.defaultMessage}" }
            is BindException ->
                e.bindingResult.fieldErrors.joinToString("\n") { "${it.field}: ${it.defaultMessage}" }
            else -> ErrorType.INVALID_REQUEST.defaultMessage
        }.ifBlank { ErrorType.INVALID_REQUEST.defaultMessage }

        val body = ApiErrorResponse(
            code = ErrorType.INVALID_REQUEST.code,
            message = msg,
            type = ErrorType.INVALID_REQUEST.name,
        )
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ApiErrorResponse> {
        val body = ApiErrorResponse(
            code = ErrorType.INVALID_REQUEST.code,
            message = e.message ?: ErrorType.INVALID_REQUEST.defaultMessage,
            type = ErrorType.INVALID_REQUEST.name,
        )
        return ResponseEntity.badRequest().body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknown(e: Exception, request: HttpServletRequest): ResponseEntity<ApiErrorResponse> {
        log.error("UnhandledException: uri={}, message={}", request.requestURI, e.message, e)

        val body = ApiErrorResponse(
            code = ErrorType.INTERNAL_ERROR.code,
            message = ErrorType.INTERNAL_ERROR.defaultMessage,
            type = ErrorType.INTERNAL_ERROR.name,
        )
        return ResponseEntity.status(500).body(body)
    }
}
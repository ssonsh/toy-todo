package com.todo.domain

import java.time.Instant

data class Member(
    val id: MemberId? = null,   // 저장 전 : null, 저장 후 : 값 존재
    val email: MemberEmail,
    val name: String,
    val createdAt: Instant? = null, // 저장 전 : null, 저장 후 : 값 존재
)

@JvmInline
value class MemberId(val value: Long)

@JvmInline
value class MemberEmail(val value: String) {
    init {
        require(value.isNotBlank()) { "Email cannot be blank" }
        require(value.contains("@")) { "Email must contain '@'" }
    }
}
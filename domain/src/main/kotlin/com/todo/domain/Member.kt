package com.todo.domain

import java.time.Instant

data class Member(
    val id: MemberId,
    val email: MemberEmail,
    val name: String,
    val createdAt: Instant,
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
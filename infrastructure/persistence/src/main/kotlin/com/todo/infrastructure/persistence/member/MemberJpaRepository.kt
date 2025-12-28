package com.todo.infrastructure.persistence.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long> {
    fun existsByEmail(email: String): Boolean
}
package com.todo.infrastructure.persistence.member

import com.todo.application.member.port.MemberRepository
import com.todo.domain.Member
import com.todo.domain.MemberEmail
import com.todo.domain.MemberId
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryAdapter (
    private val jpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member): Member =
        jpaRepository.save(member.toEntity()).toDomain()

    override fun findById(id: MemberId): Member? =
        jpaRepository.findById(id.value).orElse(null)?.toDomain()

    override fun existsByEmail(email: MemberEmail): Boolean =
        jpaRepository.existsByEmail(email.value)
}

private fun Member.toEntity(): MemberJpaEntity =
    MemberJpaEntity(
        id = this.id?.value ?: 0L, // 저장 전이면 0으로 정의 (Identity 전략 사용)
        email = email.value,
        name = name,
    )

private fun MemberJpaEntity.toDomain(): Member =
    Member(
        id = MemberId(id),
        email = MemberEmail(email),
        name = name,
        createdAt = createdAt,
    )
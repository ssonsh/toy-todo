package com.todo.application.member

import com.todo.application.member.port.MemberRepository
import com.todo.common.error.ConflictException
import com.todo.domain.Member
import com.todo.domain.MemberEmail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberCommandService (
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun register(email: String, name: String): Member {
        val memberEmail = MemberEmail(email)

        if (memberRepository.existsByEmail(memberEmail)) {
            throw ConflictException(
                message = "email already exists",
                metadata = mapOf("email" to email),
            )
        }

        val member = Member(email = memberEmail, name = name.trim())
        return memberRepository.save(member)
    }
}
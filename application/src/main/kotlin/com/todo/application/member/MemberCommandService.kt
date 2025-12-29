package com.todo.application.member

import com.todo.application.member.port.MemberRepository
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
        require(!memberRepository.existsByEmail(memberEmail)) { "email already exists" }

        val member = Member(
            email = memberEmail,
            name = name.trim(),
        )

        return memberRepository.save(member)
    }
}
package com.todo.application.member

import com.todo.application.member.port.MemberRepository
import com.todo.domain.Member
import com.todo.domain.MemberEmail
import com.todo.domain.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
open class MemberCommandService (
    private val memberRepository: MemberRepository
) {
    @Transactional
    open fun register(email: String, name: String): Member {
        val memberEmail = MemberEmail(email)
        require(!memberRepository.existsByEmail(memberEmail)) { "email already exists" }

        val member = Member(
            id = MemberId(0L),
            email = memberEmail,
            name = name.trim(),
            createdAt = Instant.now(),
        )

        return memberRepository.save(member)
    }
}
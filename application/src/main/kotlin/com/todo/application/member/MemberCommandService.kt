package com.todo.application.member

import com.todo.application.member.validator.ProfanityValidator
import com.todo.common.error.ConflictException
import com.todo.common.error.InvalidRequestException
import com.todo.domain.member.Member
import com.todo.domain.member.MemberEmail
import com.todo.domain.member.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberCommandService (
    private val memberRepository: MemberRepository,
    private val profanityValidator: ProfanityValidator,
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

        val trimmedName = name.trim()
        if (profanityValidator.containsProfanity(trimmedName)) {
            throw InvalidRequestException(
                message = "name contains profanity",
                metadata = mapOf("name" to trimmedName),
            )
        }

        val member = Member(email = memberEmail, name = trimmedName)
        return memberRepository.save(member)
    }
}
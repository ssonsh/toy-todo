package com.todo.application.member.port

import com.todo.domain.Member
import com.todo.domain.MemberEmail
import com.todo.domain.MemberId

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: MemberId): Member?
    fun existsByEmail(email: MemberEmail): Boolean
}
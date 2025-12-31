package com.todo.domain.member.port

import com.todo.domain.member.Member
import com.todo.domain.member.MemberEmail
import com.todo.domain.member.MemberId

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: MemberId): Member?
    fun existsByEmail(email: MemberEmail): Boolean
}
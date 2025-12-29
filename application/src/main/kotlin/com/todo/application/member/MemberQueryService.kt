package com.todo.application.member

import com.todo.application.member.port.MemberRepository
import com.todo.common.error.NotFoundException
import com.todo.domain.Member
import com.todo.domain.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberQueryService (
    private val memberRepository: MemberRepository
){
    @Transactional(readOnly = true)
    fun getById(id: Long): Member =
        memberRepository.findById(MemberId(id)) ?: throw NotFoundException("Member with id $id not found")
}
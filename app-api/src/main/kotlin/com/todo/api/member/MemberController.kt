package com.todo.api.member

import com.todo.api.member.rqrs.MemberRs
import com.todo.api.member.rqrs.RegisterMemberRq
import com.todo.application.member.MemberCommandService
import com.todo.application.member.MemberQueryService
import com.todo.domain.member.Member
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/v1/members"])
class MemberController (
    private val memberCommandService: MemberCommandService,
    private val memberQueryService: MemberQueryService,
){
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@RequestBody req: RegisterMemberRq): MemberRs =
        memberCommandService.register(req.email, req.name).toResponse()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): MemberRs =
        memberQueryService.getById(id).toResponse()
}

private fun Member.toResponse(): MemberRs {

    val memberId = requireNotNull(id) { "Member id must be set when mapping to response" }
    val created = requireNotNull(createdAt) { "Member createdAt must be set when mapping to response" }

    return MemberRs(
        id = memberId.value,
        email = email.value,
        name = name,
        createdAt = created.toString(),
    )
}

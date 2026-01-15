package com.todo.api.member.rqrs

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class RegisterMemberRq (
    @field:NotBlank(message = "이메일은 필수입니다")
    @field:Email(message = "올바른 이메일 형식이 아닙니다")
    val email: String,
    
    @field:NotBlank(message = "이름은 필수입니다")
    @field:Size(max = 15, message = "이름은 최대 15자까지 입력할 수 있습니다")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9가-힣\\s()\\[\\]+\\-&/_]+$",
        message = "이름에 허용되지 않는 특수문자가 포함되어 있습니다. 사용 가능: ( ) [ ] + - & / _"
    )
    val name: String,
)
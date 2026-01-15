package com.todo.application.member.validator

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class ProfanityValidator(
    private val restClient: RestClient
) {
    fun containsProfanity(text: String): Boolean {
        val response = restClient.get()
            .uri("http://www.purgomalum.com/service/containsprofanity?text={text}", text)
            .retrieve()
            .body(String::class.java)
        
        return response?.toBoolean() ?: false
    }
}

package com.todo.api.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class JacksonConfig {
    
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            // Kotlin 지원
            registerModule(kotlinModule())
            
            // Java 8 날짜/시간 지원
            registerModule(JavaTimeModule())
            
            // 날짜를 타임스탬프가 아닌 ISO-8601 형식으로 직렬화
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            
            // 알 수 없는 속성 무시
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            
            // null 값 필드 제외 (선택사항)
            // setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
}

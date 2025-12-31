package com.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.todo"])
class TodoApplication

fun main(args: Array<String>) {
    runApplication<TodoApplication>(*args)
}
package com.todo.infrastructure.persistence.member

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant

@Entity
@Table(
    name = "members",
    uniqueConstraints = [UniqueConstraint(name = "uk_member_email", columnNames = ["email"])]
)
class MemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 200)
    val email: String,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(nullable = false)
    val createdAt: Instant,
)
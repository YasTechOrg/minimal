package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.data.PasswordEncoder
import org.yastech.minimal.tables.User
import org.yastech.minimal.tables.UserRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class UserService
(
    private var repository: UserRepository,
    private var passwordEncoder: PasswordEncoder
)
{
    fun get(id: String): User
    {
        return repository.findById(id).block()!!
    }

    fun exist(id: String): Boolean
    {
        return repository.existsById(id).block()!!
    }

    fun add(user: User)
    {
        user.password = passwordEncoder.encoder().encode(user.password)

        user.forgot = false

        user.forgotCode = null

        user.accepted = false

        repository.save(user).subscribe()
    }
}
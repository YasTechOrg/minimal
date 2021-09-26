package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.tables.User
import org.yastech.minimal.tables.UserRepository

@Service
class UserService
(
    private var repository: UserRepository,
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
}
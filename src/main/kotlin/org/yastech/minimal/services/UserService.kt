package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.data.PasswordEncoder
import org.yastech.minimal.data.USERS
import org.yastech.minimal.tables.User
import org.yastech.minimal.tables.UserRepository

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

        user.products = mutableListOf()

        repository.save(user).subscribe()
    }

    fun addTestShop(user: User)
    {
        user.password = passwordEncoder.encoder().encode(user.password)

        user.forgot = true

        user.forgotCode = null

        user.accepted = true

        user.products = mutableListOf()

        repository.save(user).subscribe()
    }

    fun update(user: User)
    {
        repository.save(user).subscribe()
    }

    fun getShop(id: String): User
    {
        return repository.findByRoleAndEmail(USERS.SHOP, id).block()!!
    }

    fun getAllShops(): MutableList<User>
    {
        return repository.findAllByRole(USERS.SHOP).collectList().block()!!
    }
}
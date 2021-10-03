package org.yastech.minimal.tables

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.yastech.minimal.data.USERS
import reactor.core.publisher.Mono

@Document
data class User
(
    @Id
    var email: String,
    var firstname: String?,
    var lastname: String?,
    var password: String,
    var role: USERS?,
    var forgot: Boolean? = false,
    var forgotCode: String?,
    var confirmCode: String?,
    var accepted: Boolean?,
    var products: MutableList<String>?,
    var name: String?,
)

interface UserRepository : ReactiveMongoRepository<User, String>
{
    fun findByRoleAndEmail(role: USERS, email: String): Mono<User>
}
package org.yastech.minimal.tables

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.yastech.minimal.data.USERS

@Document
data class User
(
    @Id
    var email: String,
    var firstname: String,
    var lastname: String,
    var password: String,
    var role: USERS,
)

interface UserRepository : ReactiveMongoRepository<User, String>
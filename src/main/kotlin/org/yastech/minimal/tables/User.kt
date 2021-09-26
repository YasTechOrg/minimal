package org.yastech.minimal.tables

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

@Document
data class User
(
    @Id
    var email: String,
    var firstname: String,
    var lastname: String,
    var password: String,
)

interface UserRepository : ReactiveMongoRepository<User, String>
{

}
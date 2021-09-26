package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.tables.UserRepository

@Service
class UserService
(
    private var repository: UserRepository,
)
{

}
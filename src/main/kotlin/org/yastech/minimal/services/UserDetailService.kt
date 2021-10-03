package org.yastech.minimal.services

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.yastech.minimal.models.UserDetail

@Service
class UserDetailService
(
    private var userService: UserService
)
    : UserDetailsService
{
    override fun loadUserByUsername(username: String?): UserDetails
    {
        val user = userService.get(username!!)
        return UserDetail(
            user.email,
            user.password,
            user.firstname,
            user.lastname,
            mutableListOf(SimpleGrantedAuthority(user.role!!.name)),
            user.forgot,
            user.forgotCode,
            user.confirmCode,
            user.products,
            user.name
        )
    }
}
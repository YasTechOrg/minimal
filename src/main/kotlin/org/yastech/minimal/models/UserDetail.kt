package org.yastech.minimal.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.yastech.minimal.tables.User

class UserDetail
(
    private var email: String,
    private var password: String,
    private var firstname: String?,
    private var lastname: String?,
    private var authorities:  MutableCollection<out GrantedAuthority>,
    private var forgot: Boolean? = false,
    private var forgotCode: String?,
    private var confirmCode: String?,
    private var products: MutableList<String>?,
    private var name: String?,
) : UserDetails
{
    fun build(user: User): UserDetail
    {
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

    override fun getPassword(): String
    {
        return password
    }

    override fun getUsername(): String
    {
        return email
    }

    override fun isAccountNonExpired(): Boolean
    {
        return true
    }

    override fun isAccountNonLocked(): Boolean
    {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean
    {
        return true
    }

    override fun isEnabled(): Boolean
    {
        return true
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>
    {
        return authorities
    }
}
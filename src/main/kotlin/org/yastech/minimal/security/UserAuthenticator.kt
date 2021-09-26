package org.yastech.weeki.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserAuthenticator
(
    private var authenticationManager: AuthenticationManager
)
{
    fun getAuthenticate(username: String, password: String): Authentication
    {
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }
}
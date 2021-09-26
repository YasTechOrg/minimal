package org.yastech.minimal.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AuthEntryPointJwt : AuthenticationEntryPoint
{
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException)
    {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "system : user is unauthorized!")
    }

}
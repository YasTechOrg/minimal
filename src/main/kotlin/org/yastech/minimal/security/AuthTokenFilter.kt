package org.yastech.minimal.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import org.yastech.minimal.data.JWTParser
import org.yastech.minimal.services.UserDetailService
import org.yastech.minimal.services.UserService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class AuthTokenFilter
(
    private var jwtUtils: JWTUtils,
    private var userDetailsService: UserDetailService,
    private var jwtParser: JWTParser,
    private var userService: UserService
)
    : OncePerRequestFilter()
{
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain)
    {
        val jwt = jwtParser.parse(request)

        if (!jwt.isNullOrEmpty() && jwtUtils.validateJwtToken(jwt))
        {
            val username: String = jwtUtils.getUserNameFromJwtToken(jwt)

            if(userService.exist(username))
            {
                val userDetails = userDetailsService.loadUserByUsername(username)

                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.authorities
                )

                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }
}
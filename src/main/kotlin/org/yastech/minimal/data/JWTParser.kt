package org.yastech.minimal.data

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import javax.servlet.http.HttpServletRequest

@Service
class JWTParser
{

    fun parse(request: HttpServletRequest): String?
    {
        val headerAuth = request.getHeader("Authorization")

        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
        {
            headerAuth.substring(7, headerAuth.length)
        }
        else null
    }
}
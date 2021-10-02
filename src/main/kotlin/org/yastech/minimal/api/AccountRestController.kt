package org.yastech.minimal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yastech.minimal.data.JWTParser
import org.yastech.minimal.models.ClientUser
import org.yastech.minimal.security.JWTUtils
import org.yastech.minimal.services.UserService
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/rest/account")
class AccountRestController
(
    private var userService: UserService,
    private var jwtUtils: JWTUtils,
    private var jwtParser: JWTParser
)
{
    @GetMapping("/user/get")
    fun getUser(request: HttpServletRequest): Mono<ClientUser>
    {
        val user = userService.get(jwtUtils.getUserNameFromJwtToken(jwtParser.parse(request)!!))

        return ClientUser(user.email, user.firstname, user.lastname).toMono()
    }
}
package org.yastech.minimal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yastech.minimal.services.UserService
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/rest/public")
class PublicRestController
(
    private var userService: UserService
)
{
    @GetMapping("/brands/get")
    fun getBrands(request: HttpServletRequest): Flux<Map<String, String>>
    {
        return userService.getAllShops().map {
            mapOf("name" to it.name!!, "email" to it.email)
        }.toFlux()
    }
}
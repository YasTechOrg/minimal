package org.yastech.minimal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yastech.minimal.data.JWTParser
import org.yastech.minimal.data.USERS
import org.yastech.minimal.models.ClientUser
import org.yastech.minimal.security.JWTUtils
import org.yastech.minimal.services.ProductService
import org.yastech.minimal.services.UserService
import org.yastech.minimal.tables.Product
import org.yastech.minimal.tables.User
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random

@RestController
@RequestMapping("/api/rest/test")
class TestRestController
(
    private var userService: UserService,
    private var productService: ProductService
)
{
    @GetMapping("/add/shop")
    fun api1(request: HttpServletRequest)
    {
        userService.addTestShop(
            User(
                "testmail@gmail.com",
                null,
                null,
                "1q2w3e4r5t",
                USERS.SHOP,
                null,
                null,
                null,
                null,
                null,
                "puma"
            )
        )
    }

    @GetMapping("/add/product")
    fun api2(request: HttpServletRequest)
    {
        val id = "test-${Random.nextInt(100, 1000000)}"
        val u = userService.get("testmail@gmail.com")
        u.products!!.add(id)
        userService.update(u)
        productService.add(Product(
            id,
            "Man's Shirt",
            "1234",
            120000,
            true,
            140000,
            mutableListOf("id"),
            "testmail@gmail.com",
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            mapOf(),
            mutableListOf("#a832a6", "#32a834"),
            mutableListOf("hoseinaraghi84@gmail.com"),
            mutableListOf("clothes"),
            0
        ))
    }
}
package org.yastech.minimal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yastech.minimal.data.USERS
import org.yastech.minimal.services.ProductService
import org.yastech.minimal.services.SpecialSalesService
import org.yastech.minimal.services.UserService
import org.yastech.minimal.tables.Product
import org.yastech.minimal.tables.SpecialSales
import org.yastech.minimal.tables.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random

@RestController
@RequestMapping("/api/rest/test")
class TestRestController
(
    private var userService: UserService,
    private var productService: ProductService,
    private var specialProductService: SpecialSalesService,
)
{
    @GetMapping("/add/shop")
    fun api1(request: HttpServletRequest)
    {
        userService.addTestShop(
            User(
                "testmail2@gmail.com",
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
        val u = userService.get("testmail2@gmail.com")
        u.products!!.add(id)
        userService.update(u)
        productService.add(Product(
            id,
            "Man's Shirt",
            "1234",
            Random.nextLong(1000000, 999999999),
            true,
            140000,
            mutableListOf("id"),
            "testmail2@gmail.com",
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            mapOf(),
            mutableListOf("#a832a6", "#32a834"),
            mutableListOf("hoseinaraghi84@gmail.com"),
            mutableListOf("shirts"),
            0
        ))
    }

    @GetMapping("/add/sp/{t}")
    fun api3(request: HttpServletRequest, @PathVariable t: String)
    {
        val id = "special-${Random.nextInt(100, 1000000)}"
        specialProductService.add(SpecialSales(
            id,
            t,
            productService.get(t).name!!,
            userService.getShop(productService.get(t).publisher!!).name!!,
            productService.get(t).price!!,
        ))

    }
}
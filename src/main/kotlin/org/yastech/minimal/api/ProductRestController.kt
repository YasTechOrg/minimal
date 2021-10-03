package org.yastech.minimal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yastech.minimal.models.ProductDetail
import org.yastech.minimal.services.ProductService
import org.yastech.minimal.services.SpecialSalesService
import org.yastech.minimal.services.UserService
import org.yastech.minimal.tables.SpecialSales
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random

@RestController
@RequestMapping("/api/rest/product")
class ProductRestController
(
    private var productService: ProductService,
    private var userService: UserService,
    private var specialSalesService: SpecialSalesService
)
{
    @GetMapping("/get/off/{count}")
    fun getInSales(request: HttpServletRequest, @PathVariable count: Int): Flux<ProductDetail>
    {
        var products = productService.allSalesSortByBuy()

        if (products.size >= count)
        {
            products = products.subList(0, count)
        }

        return products.map {
            ProductDetail(
                it.id,
                it.images[0],
                it.code,
                it.off,
                it.offValue,
                it.price,
                userService.getShop(it.publisher!!).name,
                it.colors,
                it.like,
                it.categories
            )
        }.toFlux()
    }

    @GetMapping("/get/specials/is")
    fun getIsSpecialSales(request: HttpServletRequest): Mono<Boolean>
    {
       return specialSalesService.all().isEmpty().toMono()
    }

    @GetMapping("/get/specials/random")
    fun getSpecialSales(request: HttpServletRequest): Mono<SpecialSales>
    {
        val specialSales = specialSalesService.all()

        return specialSales[Random.nextInt(0, specialSales.size -1)].toMono()
    }

    @GetMapping("/get/newest")
    fun getNewest(request: HttpServletRequest): Flux<ProductDetail>
    {
        val products = productService.all()

        products.sortByDescending { LocalDateTime.parse(it.date, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }

        return products.subList(0, 6).map {
            ProductDetail(
                it.id,
                it.images[0],
                it.code,
                it.off,
                it.offValue,
                it.price,
                userService.getShop(it.publisher!!).name,
                it.colors,
                it.like,
                it.categories
            )
        }.toFlux()
    }
}
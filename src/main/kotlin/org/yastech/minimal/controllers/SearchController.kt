package org.yastech.minimal.controllers

import com.google.gson.Gson
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Controller
import org.yastech.minimal.dispatcher.SearchDispatcher
import org.yastech.minimal.models.ProductDetail
import org.yastech.minimal.models.SearchModel
import org.yastech.minimal.services.ProductService
import org.yastech.minimal.services.UserService


@Controller
class SearchController
(
    private val searchDispatcher: SearchDispatcher,
    private val productService: ProductService,
    private val userService: UserService
)
{
    @Suppress("UNCHECKED_CAST")
    @MessageMapping("/do-search")
    fun start(stompHeaderAccessor: StompHeaderAccessor)
    {
        // Get Search Values And Convert To Map
        val searchItems = Gson()
            .fromJson(
                stompHeaderAccessor.getNativeHeader("search")!![0].toString(),
                Map::class.java
            )

        // Execute Search Query
        val searchResult = productService.doSearch(
            SearchModel(
                searchItems["text"].toString(),
                searchItems["category"].toString(),
                searchItems["brands"] as MutableList<String>,
                (searchItems["min_price"] as Double).toLong(),
                (searchItems["max_price"] as Double).toLong(),
            )
        )

        val finalResult = searchResult.map {
            ProductDetail(
                it.id,
                it.name,
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
        }

        // Convert Result To Json
        val jsonSearchResult = Gson()
            .toJson(finalResult)

        Thread.sleep(1000)

        // Send Result To Client
        searchDispatcher.send(stompHeaderAccessor.sessionId!!, jsonSearchResult)

    }
}
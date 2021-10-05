package org.yastech.minimal.controllers

import com.google.gson.Gson
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Controller
import org.yastech.minimal.dispatcher.SearchDispatcher
import org.yastech.minimal.models.SearchModel
import org.yastech.minimal.services.ProductService


@Controller
class SearchController
(
    private val searchDispatcher: SearchDispatcher,
    private val productService: ProductService
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

        // Convert Result To Json
        val jsonSearchResult = Gson()
            .toJson(searchResult)

        Thread.sleep(1000)

        // Send Result To Client
        searchDispatcher.send(stompHeaderAccessor.sessionId!!, jsonSearchResult)

    }
}
package org.yastech.minimal.tables

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

@Document
data class Product
(
    @Id
    var id: String,
    var name: String?,
    var code: String?,
    var price: Long?,
    var off: Boolean?,
    var offValue: Long?,
    var images: MutableList<String>,
    var publisher: String?,
    var date: String?,
    var details: Map<String, Any>?,
    var colors: MutableList<String>,
    var like: MutableList<String>?,
    var categories: MutableList<String>?,
    var buy: Int?,
)

interface ProductRepository : ReactiveMongoRepository<Product, String>
{
    fun findAllByOffIsTrueOrderByBuyAsc(): Flux<Product>
}
package org.yastech.minimal.tables

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

@Document
data class SpecialSales
(
    @Id
    var id: String,
    var pId: String,
    var name: String,
    var publisher: String,
    var price: Long,
)

interface SpecialSalesRepository : ReactiveMongoRepository<SpecialSales, String>
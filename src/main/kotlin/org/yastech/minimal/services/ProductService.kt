package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.tables.Product
import org.yastech.minimal.tables.ProductRepository

@Service
class ProductService
(
    private val productRepository: ProductRepository
)
{
    fun add(product: Product)
    {
        productRepository.save(product).subscribe()
    }

    fun allSalesSortByBuy(): MutableList<Product>
    {
        return productRepository.findAllByOffIsTrueOrderByBuyAsc().collectList().block()!!
    }

    fun get(id: String): Product
    {
        return productRepository.findById(id).block()!!
    }


    fun all(): MutableList<Product>
    {
        return productRepository.findAll().collectList().block()!!
    }
}
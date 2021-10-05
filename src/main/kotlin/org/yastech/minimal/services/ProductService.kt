package org.yastech.minimal.services

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.yastech.minimal.models.SearchModel
import org.yastech.minimal.tables.Product
import org.yastech.minimal.tables.ProductRepository
import java.util.regex.Pattern

@Service
class ProductService
(
    private val productRepository: ProductRepository,
    private val templateRepository: ReactiveMongoTemplate,
    private val userService: UserService
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

    fun allTopSales(): MutableList<Product>
    {
        return productRepository.findAllByOrderByBuyAsc().collectList().block()!!
    }

    fun get(id: String): Product
    {
        return productRepository.findById(id).block()!!
    }


    fun all(): MutableList<Product>
    {
        return productRepository.findAll().collectList().block()!!
    }

    fun doSearch(search: SearchModel): MutableList<Product>
    {
        val filterQueries = Criteria.where("name").regex(Pattern.compile(search.text!!, Pattern.CASE_INSENSITIVE))

        filterQueries.and("price").gte(search.minPrice).lte(search.maxPrice)

        if(search.brands!!.isNotEmpty())
        {
            val q = mutableListOf<Criteria>()

            search.brands!!.forEach {
                q.add(Criteria.where("publisher").`is`(it))
            }

            filterQueries.orOperator(q)
        }
        /*
        search.brands!!.forEach {
            filterQueries.and("publisher").`is`(it)
        }*/
        if(!search.categories.isNullOrEmpty())
        {
            filterQueries.and("categories").`in`(search.categories)
        }

        return templateRepository.find(
            Query().addCriteria(filterQueries),
            Product::class.java
        ).collectList().block()!!
    }
}
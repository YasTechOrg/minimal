package org.yastech.minimal.services

import org.springframework.stereotype.Service
import org.yastech.minimal.tables.Product
import org.yastech.minimal.tables.ProductRepository
import org.yastech.minimal.tables.SpecialSales
import org.yastech.minimal.tables.SpecialSalesRepository

@Service
class SpecialSalesService
(
    private val specialSalesRepository: SpecialSalesRepository
)
{
    fun all(): MutableList<SpecialSales>
    {
        return specialSalesRepository.findAll().collectList().block()!!
    }

    fun add(specialSales: SpecialSales)
    {
        specialSalesRepository.save(specialSales).subscribe()
    }
}
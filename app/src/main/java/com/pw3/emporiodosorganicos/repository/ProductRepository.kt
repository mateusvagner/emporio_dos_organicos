package com.pw3.emporiodosorganicos.repository

import com.pw3.emporiodosorganicos.database.entity.ProductEntity

interface ProductRepository {

    fun getAll(): List<ProductEntity>

    fun insertAll(vararg products: ProductEntity)

    fun delete(product: ProductEntity)
}

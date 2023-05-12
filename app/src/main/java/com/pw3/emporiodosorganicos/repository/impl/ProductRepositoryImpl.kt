package com.pw3.emporiodosorganicos.repository.impl

import com.pw3.emporiodosorganicos.database.dao.ProductDao
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
): ProductRepository {

    override fun getAll(): List<ProductEntity> {
        return productDao.getAll()
    }

    override fun insertAll(vararg products: ProductEntity) {
        productDao.insertAll(*products)
    }

    override fun delete(product: ProductEntity) {
        productDao.delete(product)
    }
}

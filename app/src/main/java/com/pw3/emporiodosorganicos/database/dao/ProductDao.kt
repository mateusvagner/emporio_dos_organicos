package com.pw3.emporiodosorganicos.database.dao

import androidx.room.*
import com.pw3.emporiodosorganicos.database.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product WHERE id=:id ")
    fun findById(id: Int): ProductEntity

    @Query("SELECT * FROM product")
    fun getAll(): List<ProductEntity>

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

    @Update
    fun updateAll(vararg products: ProductEntity)

}
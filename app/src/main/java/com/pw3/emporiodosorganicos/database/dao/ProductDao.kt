package com.pw3.emporiodosorganicos.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pw3.emporiodosorganicos.database.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<ProductEntity>

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

}
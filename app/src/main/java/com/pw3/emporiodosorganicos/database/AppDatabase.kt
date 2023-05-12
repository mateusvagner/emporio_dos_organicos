package com.pw3.emporiodosorganicos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pw3.emporiodosorganicos.database.dao.ProductDao
import com.pw3.emporiodosorganicos.database.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}

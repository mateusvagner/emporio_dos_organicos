package com.pw3.emporiodosorganicos.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "value") var value: String,
    @ColumnInfo(name = "supplier") var supplier: String,
)

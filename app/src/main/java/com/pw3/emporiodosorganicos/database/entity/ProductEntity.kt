package com.pw3.emporiodosorganicos.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val uid: Int? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "value") val value: String?,
    @ColumnInfo(name = "supplier") val supplier: String?,
)
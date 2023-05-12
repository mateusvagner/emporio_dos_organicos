package com.pw3.emporiodosorganicos.di

import android.content.Context
import androidx.room.Room
import com.pw3.emporiodosorganicos.database.AppDatabase
import com.pw3.emporiodosorganicos.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext applicationContext: Context
    ) = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "database-name"
    ).build()

    @Provides
    @Singleton
    fun providesProductDao(db: AppDatabase): ProductDao = db.productDao()

}
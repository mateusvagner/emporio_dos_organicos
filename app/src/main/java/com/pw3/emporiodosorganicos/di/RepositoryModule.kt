package com.pw3.emporiodosorganicos.di

import com.pw3.emporiodosorganicos.repository.ProductRepository
import com.pw3.emporiodosorganicos.repository.impl.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsProductRepository(impl: ProductRepositoryImpl): ProductRepository
}

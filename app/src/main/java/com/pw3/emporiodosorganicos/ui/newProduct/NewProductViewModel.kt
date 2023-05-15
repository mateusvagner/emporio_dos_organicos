package com.pw3.emporiodosorganicos.ui.newProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _onProductSaved: MutableLiveData<Boolean> = MutableLiveData(false)
    val onProductSaved: LiveData<Boolean> get() = _onProductSaved

    private val _onSaveFailed: MutableLiveData<Boolean> = MutableLiveData(false)
    val onSaveFailed: LiveData<Boolean> get() = _onSaveFailed

    fun saveProduct(product: ProductEntity) {
        try {
            productRepository.insertAll(product)
            _onProductSaved.value = true
        } catch (exception: RuntimeException) {
            _onSaveFailed.value = true
        }
    }


}
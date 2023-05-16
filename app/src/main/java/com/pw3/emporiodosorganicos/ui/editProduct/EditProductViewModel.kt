package com.pw3.emporiodosorganicos.ui.editProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.repository.ProductRepository
import com.pw3.emporiodosorganicos.util.SingleLiveEventLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _product: MutableLiveData<ProductEntity> = MutableLiveData()
    val product: LiveData<ProductEntity> get() = _product

    private val _onProductSaved: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val onProductSaved: LiveData<Boolean> get() = _onProductSaved

    private val _onSaveFailed: MutableLiveData<Boolean> = SingleLiveEventLiveData()
    val onSaveFailed: LiveData<Boolean> get() = _onSaveFailed

    fun findProduct(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val product = productRepository.findById(id)
            _product.postValue(product)
        }
    }

    fun updateProduct(product: ProductEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productRepository.updateAll(product)
                _onProductSaved.postValue(true)
            } catch (exception: RuntimeException) {
                _onSaveFailed.postValue(true)
            }
        }
    }
}

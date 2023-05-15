package com.pw3.emporiodosorganicos.ui.newProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productRepository.insertAll(product)
                _onProductSaved.postValue(true)
            } catch (exception: RuntimeException) {
                _onSaveFailed.postValue(true)
            }
        }
    }
}

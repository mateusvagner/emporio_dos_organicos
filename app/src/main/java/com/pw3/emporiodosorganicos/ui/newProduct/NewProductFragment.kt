package com.pw3.emporiodosorganicos.ui.newProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewProductFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() {
        binding.saveButton.setOnClickListener {
            val allEntriesValid = checkUserEntries()
            if (!allEntriesValid)
                return@setOnClickListener

            val product = ProductEntity(
                name = binding.editTextName.text.toString(),
                description = binding.editTextName.text.toString(),
                value = binding.editTextName.text.toString(),
                supplier = binding.editTextName.text.toString(),
            )

            viewModel.saveProduct(product)
        }
    }

    private fun checkUserEntries(): Boolean {
        var entriesValid = true

        if (binding.editTextName.text.isNullOrBlank()) {
            binding.editTextName.error = "O nome deve ser preenchido"
            entriesValid = false
        } else {
            binding.editTextName.error = null
        }

        if (binding.editTextDescription.text.isNullOrBlank()) {
            binding.editTextDescription.error = "A descrição deve ser preenchida"
            entriesValid = false
        } else {
            binding.editTextDescription.error = null
        }

        if (binding.editTextValue.text.isNullOrBlank()) {
            binding.editTextValue.error = "O valor deve ser preenchido"
            entriesValid = false
        } else {
            binding.editTextValue.error = null
        }

        if (binding.editTextSupplier.text.isNullOrBlank()) {
            binding.editTextSupplier.error = "O fornecedor deve ser preenchido"
            entriesValid = false
        } else {
            binding.editTextSupplier.error = null
        }

        return entriesValid
    }

    private fun setupObservers() {
        viewModel.onProductSaved.observe(viewLifecycleOwner) {
            //TODO show snackbar success
        }

        viewModel.onSaveFailed.observe(viewLifecycleOwner) {
            //TODO show snackbar error
        }
    }
}
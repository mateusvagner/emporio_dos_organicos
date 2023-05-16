package com.pw3.emporiodosorganicos.ui.editProduct

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.databinding.FragmentNewProductBinding
import com.pw3.emporiodosorganicos.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProductFragment : Fragment() {

    private var _binding: FragmentNewProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditProductViewModel by viewModels()

    private val args: EditProductFragmentArgs by navArgs()

    private var product: ProductEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setupListeners()
        setupObservers()


        viewModel.findProduct(args.productId)
    }

    private fun setupListeners() {
        binding.saveButton.setOnClickListener {
            val imm: InputMethodManager? =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(requireView().windowToken, 0)

            val allEntriesValid = checkUserEntries()
            if (!allEntriesValid)
                return@setOnClickListener

            product?.let {
                it.name = binding.editTextName.text.toString()
                it.description = binding.editTextDescription.text.toString()
                it.value = binding.editTextValue.text.toString()
                it.supplier = binding.editTextSupplier.text.toString()

                viewModel.updateProduct(it)
            }
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
        viewModel.product.observe(viewLifecycleOwner) {
            product = it

            binding.editTextName.setText(it.name)
            binding.editTextDescription.setText(it.description)
            binding.editTextValue.setText(it.value)
            binding.editTextSupplier.setText(it.supplier)
        }

        viewModel.onProductSaved.observe(viewLifecycleOwner) { saved ->
            if (saved)
                showSnackbar("Produto salvo com sucessp!")
        }

        viewModel.onSaveFailed.observe(viewLifecycleOwner) { failed ->
            if (failed)
                showSnackbar("Ops! Algo deu errado.")
        }
    }
}
package com.pw3.emporiodosorganicos.ui.newProduct


import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.pw3.emporiodosorganicos.R
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.databinding.FragmentNewProductBinding
import com.pw3.emporiodosorganicos.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewProductFragment : Fragment() {

    private var _binding: FragmentNewProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewProductViewModel by viewModels()

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
        setupMenu()
        setupListeners()
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        binding.editTextName.text = null
        binding.editTextDescription.text = null
        binding.editTextValue.text = null
        binding.editTextSupplier.text = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.about_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.about -> {
                        findNavController().popBackStack()
                        findNavController().navigate(
                            NewProductFragmentDirections.actionGlobalAbout()
                        )
                        return true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupListeners() {
        binding.saveButton.setOnClickListener {
            val imm: InputMethodManager? =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(requireView().windowToken, 0)

            val allEntriesValid = checkUserEntries()
            if (!allEntriesValid)
                return@setOnClickListener

            val product = ProductEntity(
                name = binding.editTextName.text.toString(),
                description = binding.editTextDescription.text.toString(),
                value = binding.editTextValue.text.toString(),
                supplier = binding.editTextSupplier.text.toString(),
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
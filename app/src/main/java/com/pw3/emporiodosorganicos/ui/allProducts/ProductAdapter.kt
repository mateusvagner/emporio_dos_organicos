package com.pw3.emporiodosorganicos.ui.allProducts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pw3.emporiodosorganicos.R
import com.pw3.emporiodosorganicos.database.entity.ProductEntity
import com.pw3.emporiodosorganicos.databinding.LayoutProductItemBinding

class ProductAdapter(private val onDeleteClicked: (ProductEntity) -> Unit) :
    ListAdapter<ProductEntity, ProductAdapter.ProductItemViewHolder>(
        CoffeeDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductItemViewHolder(
            LayoutProductItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductItemViewHolder(private val binding: LayoutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductEntity) {
            val context = binding.root.context

            binding.name.text = product.name
            binding.description.text = product.description
            binding.value.text = context.getString(R.string.value_currency, product.value)

            binding.deleteButton.setOnClickListener {
                onDeleteClicked(product)
            }
        }
    }

    companion object {
        class CoffeeDiffUtil : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

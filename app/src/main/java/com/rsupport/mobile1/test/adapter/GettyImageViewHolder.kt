package com.rsupport.mobile1.test.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.entity.GettyImage
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemGettyImageBinding.bind(itemView)

    fun bind(item: GettyImage) {
        binding.ivGetty.load(item.imageUrl) {
            crossfade(true)
            listener(
                onStart = {
                    binding.pbImage.isVisible = true
                },
                onSuccess = { _, _ ->
                    binding.pbImage.isVisible = false
                },
                onError = { _, _ ->
                    binding.pbImage.isVisible = false
                }
            )
        }
    }
}
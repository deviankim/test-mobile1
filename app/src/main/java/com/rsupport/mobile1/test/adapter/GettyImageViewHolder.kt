package com.rsupport.mobile1.test.adapter

import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.GettyImage
import com.example.image_loader.load
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemGettyImageBinding.bind(itemView)

    fun bind(item: GettyImage) {
        binding.ivGetty.load(item.imageUrl) {
            listener(
                onStart = {
                    binding.pbImage.isVisible = true
                    binding.ivError.isGone = true
                },
                onSuccess = { _ ->
                    binding.pbImage.isVisible = false
                    binding.ivError.isGone = true
                },
                onError = { _ ->
                    binding.pbImage.isVisible = false
                    binding.ivError.isGone = false
                }
            )
        }
    }
}

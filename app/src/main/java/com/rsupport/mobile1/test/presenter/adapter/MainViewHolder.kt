package com.rsupport.mobile1.test.presenter.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.transform.RoundedCornersTransformation
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData


class MainViewHolder(private val binding: ItemGettyImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: MainUiData) {
        val context = binding.root.context
        val imageLoader = ImageLoader.Builder(context)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .build()

        binding.apply {
            itemAuthor.text = data.author
            itemDate.text = data.date

            itemImageView.load(data.thumbnailUrl, imageLoader) {
                transformations(RoundedCornersTransformation(15f))
                crossfade(true)
            }
        }
    }
}

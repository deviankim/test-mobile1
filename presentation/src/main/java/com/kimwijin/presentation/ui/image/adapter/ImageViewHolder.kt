package com.kimwijin.presentation.ui.image.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimwijin.domain.model.ImageInfo
import com.kimwijin.presentation.R
import com.kimwijin.presentation.databinding.ItemImageBinding

/**
 * Image RecyclerView의 Item 항목을 나타내기 위한 ViewHolder 클래스 입니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
class ImageViewHolder (
    private val binding: ItemImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageInfo: ImageInfo?) {
        if (imageInfo == null) return

        binding.image = imageInfo
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ImageViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            val binding = ItemImageBinding.bind(view)
            return ImageViewHolder(binding)
        }
    }
}
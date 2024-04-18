package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemImageBinding
import com.rsupport.mobile1.test.model.Image
import javax.inject.Singleton

@Singleton
class ImageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: MutableList<Image> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items.let {
            (holder as ImageViewHolder).bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ImageViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            binding.imageUrl = item.src
        }
    }

    fun addImageList(images: List<Image>) {
        items.addAll(images)
        notifyItemRangeInserted(items.size + 1, images.size)
    }
}
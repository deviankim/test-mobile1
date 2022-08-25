package com.rsupport.mobile1.test.activity.Presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.activity.Data.Entity.ImageEntity
import com.rsupport.mobile1.test.databinding.ItemImageBinding


class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var ImageList = listOf<ImageEntity>()

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(imageEntity: ImageEntity) = with(binding) {
            Glide.with(binding.root)
                .load(imageEntity.url)
                .into(container1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val view = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.bindData(ImageList[position])
    }

    override fun getItemCount() = ImageList.size

    fun setData(list: List<ImageEntity>) {
        ImageList = list
        notifyDataSetChanged()
    }
}
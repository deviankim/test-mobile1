package com.rsupport.mobile1.test.activity.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.activity.data.model.GettyImage
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageAdapter : ListAdapter<GettyImage, GettyImageAdapter.GettyImageViewHolder>(DiffCallback) {

    companion object {
        private const val TAG = "GettyImageAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GettyImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGettyImageBinding.inflate(inflater, parent, false)

        val deviceWidth = parent.resources.displayMetrics.widthPixels
        val itemWidth = deviceWidth / 3

        binding.root.layoutParams.width = itemWidth
        Log.d(TAG, "onCreateViewHolder, screenWidth: $deviceWidth, itemWidth: $itemWidth")

        return GettyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GettyImageViewHolder(private val binding: ItemGettyImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: GettyImage) {
            binding.image = image
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<GettyImage>() {
    override fun areItemsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem == newItem
    }
}
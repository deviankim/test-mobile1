package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.data.GettyImage
import com.rsupport.mobile1.test.databinding.ListItemGettyImageBinding

class GettyImageAdapter : androidx.recyclerview.widget.ListAdapter<
        GettyImage, RecyclerView.ViewHolder>(GettyImageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ViewHolder private constructor(
    val binding: ListItemGettyImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GettyImage) {
        // TODO: xml과 세팅해주는 코드 넣어주기
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemGettyImageBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}

class GettyImageDiffCallback : DiffUtil.ItemCallback<GettyImage>() {
    override fun areItemsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: GettyImage, newItem: GettyImage): Boolean {
        return oldItem == newItem
    }
}
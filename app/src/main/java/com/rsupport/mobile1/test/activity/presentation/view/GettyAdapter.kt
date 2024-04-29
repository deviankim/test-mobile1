package com.rsupport.mobile1.test.activity.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel
import com.rsupport.mobile1.test.activity.presentation.ext.setImageToUrl
import com.rsupport.mobile1.test.databinding.ItemGettyBinding

class GettyAdapter (
    private val onItemClick: (String) -> Unit
) :  ListAdapter<UiGettyModel, GettyAdapter.ViewHolder>(
    ItemDiffCallback<UiGettyModel>(
        onItemsTheSame = { old, new -> old == new },
        onContentsTheSame = { old, new -> old == new }
    )
) {
    class ViewHolder(
        private val binding : ItemGettyBinding,
        private val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val imgSrc: ImageView = binding.ivGettyImage
        private val imgTitle: TextView = binding.tvGettyImageTitle

        fun onBind(
            item : UiGettyModel
        ) {
            imgTitle.text = item.title
            imgSrc.setImageToUrl(item.src)
            binding.root.setOnClickListener {
                if (item.link != null) {
                    onItemClick(item.link)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGettyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calendarItem = getItem(position)
        holder.onBind(calendarItem)
    }

}

class ItemDiffCallback<T : Any>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = onItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = onContentsTheSame(oldItem, newItem)
}
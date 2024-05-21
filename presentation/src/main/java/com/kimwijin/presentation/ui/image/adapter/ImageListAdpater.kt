package com.kimwijin.presentation.ui.image.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kimwijin.domain.model.ImageInfo

/**
 * Image RecyclerView를 표시하기 위한 어댑터 클래스 입니다.
 *
 * @author (김위진)
 * @since (2024-05-21)
 */
class ImageListAdpater : ListAdapter<ImageInfo, ImageViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ImageInfo>() {
            override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
                return oldItem.src == newItem.src
            }

            override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean =
                oldItem == newItem
        }
    }
}
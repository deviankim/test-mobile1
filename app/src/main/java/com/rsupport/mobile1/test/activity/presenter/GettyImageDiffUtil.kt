package com.rsupport.mobile1.test.activity.presenter

import androidx.recyclerview.widget.DiffUtil
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse

object GettyImageDiffUtil : DiffUtil.ItemCallback<GettyImageResponse>() {

    override fun areItemsTheSame(oldItem: GettyImageResponse, newItem: GettyImageResponse): Boolean {
        return oldItem.assetId == newItem.assetId
    }

    override fun areContentsTheSame(oldItem: GettyImageResponse, newItem: GettyImageResponse): Boolean {
        return oldItem == newItem
    }

}
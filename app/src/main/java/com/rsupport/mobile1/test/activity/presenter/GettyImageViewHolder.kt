package com.rsupport.mobile1.test.activity.presenter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageViewHolder(private val binding: ItemGettyImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gettyImageResponse: GettyImageResponse) {
        binding.image = gettyImageResponse
        binding.viewHolder = this
    }

    fun itemClick(view: View) {

    }

}

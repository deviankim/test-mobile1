package com.rsupport.mobile1.test.activity.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageAdapter: ListAdapter<GettyImageResponse, GettyImageViewHolder>(GettyImageDiffUtil)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GettyImageViewHolder {
        val binding = ItemGettyImageBinding.inflate(LayoutInflater.from(parent.context))
        return GettyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
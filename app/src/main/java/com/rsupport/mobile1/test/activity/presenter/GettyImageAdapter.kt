package com.rsupport.mobile1.test.activity.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.activity.domain.GettyImageResponse
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageAdapter(private var gettyImageList: ArrayList<GettyImageResponse> = arrayListOf()): RecyclerView.Adapter<GettyImageViewHolder>() {

    fun setData(gettyImageList: List<GettyImageResponse>) {
        this.gettyImageList.addAll(gettyImageList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GettyImageViewHolder {
        val binding = ItemGettyImageBinding.inflate(LayoutInflater.from(parent.context))
        return GettyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GettyImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int) = gettyImageList[position]

    override fun getItemCount() = gettyImageList.size
}
package com.rsupport.mobile1.test.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding

class GettyImageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var imageList: List<GettyImage> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGettyImageBinding.inflate(inflater, parent, false)

        val deviceWidth = parent.resources.displayMetrics.widthPixels
        val itemWidth = deviceWidth / 3

        binding.root.layoutParams.width = itemWidth
        Log.d("jenny", "onCreateViewHolder, screenWidth: $deviceWidth, itemWidth: $itemWidth")

        return GettyImageViewHolder(binding)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GettyImageViewHolder).bind(imageList[position])
    }

    fun setImageList(imageList: List<GettyImage>) {
        this.imageList = imageList
    }

    private inner class GettyImageViewHolder(private val binding: ItemGettyImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: GettyImage) {
            Glide.with(binding.root.context)
                .load(image.thumbUrl)
                .centerInside()
                .error(R.drawable.ic_launcher_background)
                .into(binding.gettyImageView)
        }
    }
}
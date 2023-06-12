package com.rsupport.mobile1.test.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.data.Images

/**
 * 이미지 리스트 어뎁터
 */
class ImageAdapter(imageList: ArrayList<Images>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private var _mImageList = imageList
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (!hasObservers()) {
            setHasStableIds(true)
        }

        val img = _mImageList.get(position).imageUrl
        // Glide 사용해서 이미지 세팅
        Glide.with(mContext).load(img).override(Target.SIZE_ORIGINAL).into(holder.mImageView)
    }

    override fun getItemCount(): Int {
        return _mImageList.size
    }

    /**
     * ViewHolder
     */
    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mImageView = view.findViewById<AppCompatImageView>(R.id.image)
    }
}
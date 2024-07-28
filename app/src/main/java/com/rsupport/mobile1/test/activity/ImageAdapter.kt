package com.rsupport.mobile1.test.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R

class ImageAdapter(private val context: Context, private val images: List<String>) : BaseAdapter() {

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): Any = images[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        Glide.with(context).load(images[position]).into(imageView)

        return view
    }
}
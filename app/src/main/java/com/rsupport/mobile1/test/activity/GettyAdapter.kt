package com.rsupport.mobile1.test.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R

class GettyAdapter(var items: ArrayList<GettyItem>) :RecyclerView.Adapter<GettyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_getty, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.count()
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(item : GettyItem) {
            val image = itemView.findViewById<ImageView>(R.id.image)
            val title = itemView.findViewById<TextView>(R.id.title)

            title.text = item.title
            ImageLoader.load(item.src, image)
        }
    }
}
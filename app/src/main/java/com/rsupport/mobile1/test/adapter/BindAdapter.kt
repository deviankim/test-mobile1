package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R

class BindAdapter<T> : RecyclerView.Adapter<BindAdapter.BindViewHolder>(){

    var list: Collection<T>? = null
    var clickHandler : ClickHandler<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int) = BindViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            parent.getTag(R.string.view_tag).hashCode(),
            parent,
            false
        )!!
    )


    override fun onBindViewHolder(holder: BindViewHolder, position: Int) {
        when (holder.binding) {
            is HotelItemBinding -> {
                holder.binding.apply {
                    (list as ArrayList)[position].let { item ->
                        this.item = item as NetworkData.AppItem
                        itemThumbnail.setOnClickListener {
                            clickHandler?.onClick(it.id,item)
                        }

                        itemChecked.setOnClickListener {
                            clickHandler?.onClick(it.id,item)
                        }
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int = if (list == null) 0 else list!!.size

    fun setItems(@Nullable list: Collection<T>?) {
        this.list = list
    }

    class BindViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
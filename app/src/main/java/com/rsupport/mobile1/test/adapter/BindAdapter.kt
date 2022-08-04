package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.data.GettyData
import com.rsupport.mobile1.test.databinding.GettyItemBinding

class BindAdapter<T> : RecyclerView.Adapter<BindAdapter.BindViewHolder>(){

    var list: Collection<T>? = null

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
            is GettyItemBinding -> {
                holder.binding.apply {
                    (list as ArrayList)[position].let { item ->
                        this.item = item as GettyData
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
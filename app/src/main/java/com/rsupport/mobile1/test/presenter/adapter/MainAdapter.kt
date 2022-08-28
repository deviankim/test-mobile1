package com.rsupport.mobile1.test.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rsupport.mobile1.test.databinding.ItemGettyImageBinding
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData

class MainAdapter : ListAdapter<MainUiData, MainViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemGettyImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { mainData ->
            holder.bind(mainData)
        }
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MainUiData>() {
            override fun areItemsTheSame(oldItem: MainUiData, newItem: MainUiData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: MainUiData, newItem: MainUiData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
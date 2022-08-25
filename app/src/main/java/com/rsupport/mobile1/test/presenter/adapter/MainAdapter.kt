package com.rsupport.mobile1.test.presenter.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.presenter.activity.ui_state.MainUiData

class MainAdapter: ListAdapter<MainUiData, RecyclerView.ViewHolder>(differCallback) {

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MainUiData>() {
            override fun areItemsTheSame(oldItem: MainUiData, newItem: MainUiData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MainUiData, newItem: MainUiData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
package com.example.crawlin_test.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crawlin_test.data.model.ImageData
import com.rsupport.mobile1.test.databinding.ImageItemBinding

class MainAdapter() : ListAdapter<ImageData, MainAdapter.viewHolder>(diffUtil) {

    inner class viewHolder(binding:ImageItemBinding):RecyclerView.ViewHolder(binding.root){
        val binding = binding
        fun bind(item:ImageData){
            binding.imageData = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.viewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.viewHolder, position: Int) {
        holder.bind(getItem(position))
    }
//
//    override fun getItemCount(): Int {
//        return datas.size
//    }
//
//    fun update(items : List<ImageData>) {
//        datas = items.toMutableList()
//        notifyDataSetChanged()
//    }

    // 리스트 갱신
    override fun submitList(list: List<ImageData>?) {
        super.submitList(list)
    }




    // diffUtil 추가
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageData>(){
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem.imgUrl == newItem.imgUrl
            }

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }


}



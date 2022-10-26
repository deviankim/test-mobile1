package com.rsupport.mobile1.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.databinding.ItemImageListBinding
import com.rsupport.mobile1.test.server.resp.ImageData

class ImageListAdapter(): RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    lateinit var mListener: OnItemClickListener
    lateinit var itemList: ArrayList<ImageData>

    fun setList (itemList: ArrayList<ImageData>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    inner class ViewHolder : RecyclerView.ViewHolder {

        var binding: ItemImageListBinding
        constructor(binding: ItemImageListBinding) : super(binding.getRoot()) {
            this.binding = binding
            this.binding.viewHolder = this
        }

        fun bind(item: ImageData) {
            binding.item = item
        }

        //아이템 터치 이벤트
        fun onItemClick() {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                if (mListener != null) {
                    mListener.onItemClick(position, binding.item!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding: ItemImageListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_image_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(itemList.get(i))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: ImageData)
    }
}
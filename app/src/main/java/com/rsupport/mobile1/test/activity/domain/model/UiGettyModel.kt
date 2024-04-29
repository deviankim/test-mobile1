package com.rsupport.mobile1.test.activity.domain.model

import androidx.recyclerview.widget.DiffUtil

data class UiGettyModel (
    val title : String, // 이미지 제목
    val src : String, // 이미지 src 랑크
    val link : String // 상세페이지 링크
){
    interface OnItemClickListener {
        fun onItemClick(item: UiGettyModel)
    }

    companion object : DiffUtil.ItemCallback<UiGettyModel>() {
        override fun areItemsTheSame(oldItem: UiGettyModel, newItem: UiGettyModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: UiGettyModel, newItem: UiGettyModel): Boolean {
            return oldItem == newItem
        }
    }

}
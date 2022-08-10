package com.rsupport.mobile1.test.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.utils.Utils

class AdapterDecoration(private val margin: Int,private val size : Int) : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val postion = parent.getChildAdapterPosition(view)
        outRect.left = margin
        outRect.top = margin

        if (postion % size == size.minus(1)){
           outRect.right = margin
        }

    }
}
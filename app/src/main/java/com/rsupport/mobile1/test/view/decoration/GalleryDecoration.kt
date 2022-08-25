package com.rsupport.mobile1.test.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GalleryDecoration(
    private val spanCount: Int,
    private val space: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (position.isStartPosition()) {
            outRect.left = space
        }

        if (position.isTopPosition()) {
            outRect.top = space
        }

        outRect.right = space
        outRect.bottom = space
    }

    private fun Int.isTopPosition(): Boolean {
        return this < spanCount
    }

    private fun Int.isStartPosition(): Boolean {
        return this % spanCount == 0
    }
}
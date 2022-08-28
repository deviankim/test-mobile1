package com.rsupport.mobile1.test.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDecoration(
    private val height: Float = 0f,
    private val paddingLeft: Float = 0f,
    private val paddingRight: Float = 0f,
    @ColorInt private val color: Int = Color.TRANSPARENT,
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + paddingLeft
        val right = parent.width - parent.paddingEnd - paddingRight

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}
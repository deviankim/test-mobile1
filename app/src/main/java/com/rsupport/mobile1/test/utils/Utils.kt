package com.rsupport.mobile1.test.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.adapter.BindAdapter
import com.rsupport.mobile1.test.data.GettyData

object Utils {

    const val DOMAIN = "https://www.gettyimages.com/photos/collaboration?"
    const val IMAGE_URL = DOMAIN.plus("assettype=image&")
    const val GETTY_IMAGE = "div.GalleryItems-module__searchContent___DbMmK div article a figure picture img"
    const val SUCCESS = 200
    const val MARGIN = 16
    const val SPAN_COUNT = 3

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: AppCompatImageView,item : GettyData) {
        Glide.with(imageView.context)
            .load(item.src)
            .into(imageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    @JvmStatic
    @BindingAdapter("items")
    fun <T> setItems(recyclerView: RecyclerView, items : ArrayList<T>?){
        if (recyclerView.adapter == null){
            val adapter = BindAdapter<T>()
            recyclerView.adapter = adapter
        }

        recyclerView.adapter?.let { adapter ->
            (adapter as BindAdapter<T>).run {
                setItems(items)
            }
            adapter.notifyDataSetChanged()
        }
    }

    fun dialog(message : String,context: Context){
        AlertDialog.Builder(context).apply {
            setMessage(message)
                .setNegativeButton(R.string.confirm) { dialog, which ->
                    (context as AppCompatActivity).finishAffinity()
                }
            if (message == context.getString(R.string.finish)){
                setPositiveButton(R.string.cancel,null)
            }
        }.show()
    }

    fun toDp(context: Context,value : Int) :Int {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value.toFloat(),metrics).toInt()
    }
}
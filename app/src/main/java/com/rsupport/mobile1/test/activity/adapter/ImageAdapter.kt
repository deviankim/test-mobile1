package com.rsupport.mobile1.test.activity.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.databinding.ItemImageBinding
import java.net.HttpURLConnection
import java.net.URL

class ImageAdapter(private var imgUrlData: List<String>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imgUrl = imgUrlData[position]
        loadImage(holder.binding.imageRowItem, imgUrl)

        /*
            Glide.with(holder.binding.imageRowItem.context)
            .load(imgUrl)
            .into(holder.binding.imageRowItem)
        */
    }

    override fun getItemCount() = imgUrlData.size

    fun updateData(newData: List<String>) {
        imgUrlData = newData
        notifyDataSetChanged()
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        ImageLoadTask(imageView).execute(imageUrl)
    }

    private class ImageLoadTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

        override fun doInBackground(vararg params: String): Bitmap? {
            val imageUrl = params[0]
            return try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val inputStream = connection.inputStream
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }
    }
}

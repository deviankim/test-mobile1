package com.rsupport.mobile1.test.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.rsupport.mobile1.test.data.model.ImageData
import com.rsupport.mobile1.test.databinding.ItemImageBinding
import com.rsupport.mobile1.test.ui.dialog.ImageDialogFragment
import com.rsupport.mobile1.test.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Lee on 2024-08-23.
 * This RecyclerView Adapter binds image data to the UI components.
 */
class ImageAdapter(private val activity: FragmentActivity) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private var imageList: List<ImageData> = listOf()

    // Update the adapter's data set with a new list of images
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(images: List<ImageData>) {
        imageList = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageData = imageList[position]
        holder.bind(imageData)
    }

    override fun getItemCount(): Int = imageList.size

    // ViewHolder class to bind individual image data to UI components
    class ImageViewHolder(private val binding: ItemImageBinding, private val activity: FragmentActivity) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageData: ImageData) {
            val imageTitle = imageData.title.capitalizeFirstLetter()

            binding.textView.text = imageTitle
            loadImage(imageData.imageUrl)

            // Set a click listener to open the image in a dialog
            binding.imageView.setOnClickListener { showImageDialog(imageData) }

            // Long press listener to show title in Toast
            binding.imageView.setOnLongClickListener {
                val context = it.context
                Toast.makeText(context, imageTitle, Toast.LENGTH_LONG).show()
                true
            }
        }

        // Load image asynchronously
        private fun loadImage(url: String) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val bitmap = NetworkUtils.downloadImage(url)
                    withContext(Dispatchers.Main) {
                        binding.imageView.setImageBitmap(bitmap)
                        Log.d("ImageAdapter", "Image loaded successfully from URL: $url")
                    }
                } catch (e: Exception) {
                    Log.e("ImageAdapter", "Failed to load image from URL: $url", e)
                }
            }
        }

        // Show the selected image in a dialog with zoom functionality
        private fun showImageDialog(imageData: ImageData) {
            val dialogFragment = ImageDialogFragment.newInstance(imageData.imageUrl)
            dialogFragment.show(activity.supportFragmentManager, "ImageDialog")
        }

        // Helper function to capitalize the first letter of a string
        private fun String.capitalizeFirstLetter(): String {
            return if (isNotEmpty()) {
                this.substring(0, 1).uppercase() + this.substring(1).lowercase()
            } else {
                this
            }
        }
    }
}
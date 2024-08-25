package com.rsupport.mobile1.test.ui.dialog

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.rsupport.mobile1.test.databinding.FragmentImageDialogBinding
import com.rsupport.mobile1.test.utils.NetworkUtils.downloadImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Lee on 2024-08-24.
 * A dialog fragment to display an image with zoom functionality.
 * The image and its URL are saved/restored during screen rotation.
 */
class ImageDialogFragment : DialogFragment() {

    private var _binding: FragmentImageDialogBinding? = null
    private val binding get() = _binding!!

    // Variables to store image URL and bitmap
    private var imageUrl: String? = null
    private var imageBitmap: Bitmap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImageDialogBinding.inflate(inflater, container, false)
        // Restore the image URL and bitmap if available
        imageUrl = savedInstanceState?.getString("image_url") ?: imageUrl
        imageBitmap = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState?.getParcelable("image_bitmap", Bitmap::class.java)
        } else {
            savedInstanceState?.getParcelable("image_bitmap")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Display the image if already loaded, otherwise load it
        imageBitmap?.let {
            binding.photoView.setImageBitmap(it)
        } ?: run {
            imageUrl?.let {
                loadImage(it)
            }
        }

        // Set up the close button to dismiss the dialog
        binding.btnClose.setOnClickListener {
            Log.d("ImageDialogFragment", "Close button clicked")
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        // Set the dialog's width and height
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        Log.d("ImageDialogFragment", "onStart called")
    }

    // Load image from the URL manually without using Glide
    private fun loadImage(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val bitmap = downloadImage(url)
                withContext(Dispatchers.Main) {
                    binding.photoView.setImageBitmap(bitmap)
                    Log.d("ImageAdapter", "Image loaded successfully from URL: $url")
                }
            }
            catch (e: Exception){
                Log.e("ImageAdapter", "Failed to load image from URL: $url", e)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the image URL and bitmap to the instance state
        outState.putString("image_url", imageUrl)
        outState.putParcelable("image_bitmap", imageBitmap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // Factory method to create a new instance of ImageDialogFragment with an image URL
        fun newInstance(imageUrl: String): ImageDialogFragment {
            val fragment = ImageDialogFragment()
            fragment.imageUrl = imageUrl
            return fragment
        }
    }
}
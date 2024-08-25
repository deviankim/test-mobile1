package com.rsupport.mobile1.test.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.rsupport.mobile1.test.data.model.ImageData
import com.rsupport.mobile1.test.data.network.ImageApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Lee on 2024-08-23.
 * Utility functions for network operations like fetching images and downloading them as bitmaps.
 */
object NetworkUtils {
    // OkHttpClient instance for making network requests
    private val client = OkHttpClient()

    /**
     * Fetches images from the server and parses the HTML response.
     * @return A list of ImageData objects containing image URLs and titles.
     * @throws IOException if the network request fails.
     */
    suspend fun fetchImages(): List<ImageData> {
        val response = ImageApiService.instance.fetchImages()
        val images = mutableListOf<ImageData>()

        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                // Parse HTML using JSoup and extract image elements
                val document = Jsoup.parse(responseBody.string())
                val elements = document.select("img")

                for (element in elements) {
                    val url = element.attr("src")
                    val title = element.attr("alt")

                    // Validate image URL and add to the list
                    if (url.isNotEmpty() && title.isNotEmpty() && isImageUrlValid(url)) {
                        images.add(ImageData(url, title))
                    }
                }
            }
        } else {
            throw IOException("Failed to fetch images: ${response.code()}")
        }

        return images
    }

    /**
     * Downloads an image from the given URL and returns it as a Bitmap.
     * @param url The URL of the image to download.
     * @return The downloaded Bitmap, or null if the download fails.
     * @throws IOException if the network request fails.
     */
    fun downloadImage(url: String): Bitmap? {
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Failed to download image: ${response.code}")
            return BitmapFactory.decodeStream(response.body?.byteStream())
        }
    }

    /**
     * Checks if the given URL is valid and accessible by making a HEAD request.
     * @param url The URL to validate.
     * @return True if the URL is valid and accessible, false otherwise.
     */
    private fun isImageUrlValid(url: String): Boolean {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "HEAD"
            connection.connectTimeout = 1000
            connection.readTimeout = 1000
            connection.connect()

            // Check for HTTP OK or partial content status codes
            connection.responseCode in arrayOf(HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_PARTIAL)
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Checks if the network is available.
     * @param context The context used to retrieve the ConnectivityManager.
     * @return True if network is available, false otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
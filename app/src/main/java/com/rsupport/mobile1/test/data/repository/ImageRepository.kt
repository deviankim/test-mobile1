package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.model.ImageData
import com.rsupport.mobile1.test.utils.NetworkUtils

/**
 * Created by Lee on 2024-08-23.
 * This class acts as a repository to fetch image data from the network.
 * It serves as an abstraction layer between the data source (network) and the rest of the application.
 */
class ImageRepository {
    /**
     * Fetches a list of image data from the network.
     *
     * @return A list of ImageData objects retrieved from the network.
     */
    suspend fun fetchImages(): List<ImageData> {
        return NetworkUtils.fetchImages() // Fetch images using network utility
    }
}
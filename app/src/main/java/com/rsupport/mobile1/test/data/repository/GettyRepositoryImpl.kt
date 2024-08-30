package com.rsupport.mobile1.test.data.repository

import com.rsupport.mobile1.test.data.remote.GettyApi
import com.rsupport.mobile1.test.domain.repository.GettyRepository
import org.jsoup.Jsoup
import javax.inject.Inject

class GettyRepositoryImpl @Inject constructor(private val api: GettyApi): GettyRepository{
    override suspend fun getImageUrls(phrase: String): List<String> {
        return Jsoup.parse(api.getImageUrls(phrase = phrase)).body()
            .getElementsByClass(IMAGE_CLASS_NAME)
            .map { img -> img.attr(ATTRIBUTE_NAME) }
    }

    companion object {
        const val IMAGE_CLASS_NAME = "BLA_wBUJrga_SkfJ8won"
        const val ATTRIBUTE_NAME = "src"
    }
}
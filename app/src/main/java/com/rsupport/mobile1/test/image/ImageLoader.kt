package com.rsupport.mobile1.test.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.ImageView
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ImageLoader {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    fun load(view: ImageView, imageUrl: String) {
        view.viewTreeObserver?.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                downloadImage(imageUrl, view)
                view.viewTreeObserver?.removeOnPreDrawListener(this)
                return true
            }
        })
    }

    private fun downloadImage(imageUrl: String, view: ImageView) {
        val request = Request.Builder()
            .url(imageUrl)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val byteArray = response.body?.bytes()
                val bitmap = byteArray?.let { decodeSampledBitmapFromByteArray(it, view.width, view.height) }
                view.post { view.setImageBitmap(bitmap) }
            }
        })
    }

    private fun decodeSampledBitmapFromByteArray(
        byteArray: ByteArray,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, this)

            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

            inJustDecodeBounds = false
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, this)
        }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }

        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // 다음은 대상 너비와 높이를 기준으로 2의 거듭제곱인 샘플 크기 값을 계산하는 방법
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}
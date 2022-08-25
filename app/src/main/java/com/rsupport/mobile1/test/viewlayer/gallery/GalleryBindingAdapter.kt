package com.rsupport.mobile1.test.viewlayer.gallery

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.model.ui.ThumbnailInfo

@BindingAdapter("thumbnailInfo")
fun setThumbnail(iv: ImageView, thumbnailInfo: ThumbnailInfo) {
    Glide.with(iv).load(thumbnailInfo.thumbUrl).override(thumbnailInfo.thumbnailSize).into(iv)
}

private const val LICENSE_TYPE_RF = "RF"
@BindingAdapter("licenseType")
fun setLicenseType(iv: ImageView, licenseType: String) {
    when (licenseType) {
        LICENSE_TYPE_RF -> R.drawable.ic_rf
        else -> -1
    }.let {
        if (it != -1) {
            iv.setImageResource(it)
        }
    }
}
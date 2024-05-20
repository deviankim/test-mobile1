package com.kimwijin.presentation.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kimwijin.presentation.R

/**
 * 데이터 바인딩을 위한 어댑터 클래스 입니다.
 * 이 클래스는 데이터 바인딩 라이브러리에서 사용되며, XML 레이아웃 파일에서 사용자 정의 속성과
 * View 속성 간의 바인딩을 정의하는 데 사용됩니다.
 *
 * 사용자가 정의한 커스텀 속성과 해당 속성을 설정하는 메소드를 이 클래스에 정의하여,
 * XML 레이아웃에서 바인딩을 설정할 수 있습니다.
 *
 * @author (김위진)
 * @since (2024-05-20)
 */
@BindingAdapter("uriImage")
fun ImageView.setUriImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_empty_image)
        .centerCrop()
        .into(this)
}

@BindingAdapter("uriImageFitCenter")
fun ImageView.setUriImageFitCenter(url: String?) {
    if (url.isNullOrBlank()) return

    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_empty_image)
        .fitCenter()
        .into(this)
}


@BindingAdapter("text")
fun TextView.setText(text: String?) {
    this.text = text
}

@BindingAdapter("text")
fun TextView.setText(text: Int?) {
    this.text = text.toString()
}

@BindingAdapter("visibleIf")
fun View.setVisibleIf(value: Boolean) {
    isVisible = value
}
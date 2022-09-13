package com.ersinberkealemdaroglu.berkealemdarogluweek4.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R

fun ImageView.downloadFromUrl(img_src: String?) {

    img_src?.let {
        val imgUri = img_src.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.get_started_button_icon)
            )
            .into(this)
    }
}

@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, img_src: String?) {
    view.downloadFromUrl(img_src)
}


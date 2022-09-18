package com.ersinberkealemdaroglu.berkealemdarogluweek4.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R

fun ImageView.downloadFromUrl(img_src: String?) {

    img_src?.let {
        //https sorununu Bu kod yerine manifest dosyasına usesCleartextTraffic=true değeri ekleyerek giderilmiştir.
        //val imgUri = img_src.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(img_src)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.get_started_button_icon)
            )
            .into(this)
    }
}

/**
 * @param img_src marsDataModelden gelen img_src değerlerini view yardımıyla göstermemizi sağlıyor.
 */
@BindingAdapter("android:downloadImageUrl")
fun downloadImage(view: ImageView, img_src: String?) {
    view.downloadFromUrl(img_src)
}



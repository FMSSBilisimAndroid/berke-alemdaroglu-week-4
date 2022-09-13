package com.ersinberkealemdaroglu.berkealemdarogluweek4.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsDataModel(
    val price: String,
    val id: String,
    val type: String,
    val img_src: String,
) : Parcelable
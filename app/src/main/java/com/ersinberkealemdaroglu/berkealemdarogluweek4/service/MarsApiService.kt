package com.ersinberkealemdaroglu.berkealemdarogluweek4.service

import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel
import retrofit2.Call
import retrofit2.http.GET

interface MarsApiService {
    // GET, POST, UPDATE, DELETE METHODS.
    @GET("realestate")
    fun getProperties(): Call<List<MarsDataModel>>

}
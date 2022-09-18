package com.ersinberkealemdaroglu.berkealemdarogluweek4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel
import com.ersinberkealemdaroglu.berkealemdarogluweek4.service.MarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeListViewModel : ViewModel() {

    val marsLoading = MutableLiveData<Boolean>()
    private var _marsValue = MutableLiveData<List<MarsDataModel>>()
    val marsValue: LiveData<List<MarsDataModel>> = _marsValue

    fun getMarsData(): LiveData<List<MarsDataModel>> {
        marsLoading.value = true
        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsDataModel>> {
            override fun onResponse(
                call: Call<List<MarsDataModel>>,
                response: Response<List<MarsDataModel>>,
            ) {
                _marsValue.value = response.body()
                marsLoading.value = false
            }

            override fun onFailure(call: Call<List<MarsDataModel>>, t: Throwable) {
                marsLoading.value = true
            }

        })

        return marsValue
    }


}
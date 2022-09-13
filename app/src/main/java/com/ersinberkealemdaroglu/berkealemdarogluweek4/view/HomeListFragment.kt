package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.berkealemdarogluweek4.BR
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.adapter.MarsApiAdapter
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.FragmentHomeListBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel
import com.ersinberkealemdaroglu.berkealemdarogluweek4.service.MarsApi
import com.ersinberkealemdaroglu.berkealemdarogluweek4.util.SharedPreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeListFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private var marsDataModel = ArrayList<MarsDataModel>()
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_list, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sharedPreferenceClear()
        context?.let {
            sharedPreferenceManager = SharedPreferenceManager(it)
        }

        getMarsApiData()
    }

    private fun getMarsApiData() {

        MarsApi.retrofitService.getProperties().enqueue(object : Callback<List<MarsDataModel>> {
            override fun onResponse(
                call: Call<List<MarsDataModel>>,
                response: Response<List<MarsDataModel>>,
            ) {
                response.body()?.let { responseLists ->
                    marsDataModel = ArrayList(responseLists)
                    val adapter = MarsApiAdapter(ArrayList(responseLists))

                    val gridLayoutManager = GridLayoutManager(context, 2)
                    binding.apply {
                        recyclerview.layoutManager = gridLayoutManager
                        setVariable(BR.marsAdapter, adapter)
                    }

                }
            }

            override fun onFailure(call: Call<List<MarsDataModel>>, t: Throwable) {
                Toast.makeText(context, "veri gelmedi", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun sharedPreferenceClear() {
        binding.button.setOnClickListener {
            sharedPreferenceManager.saveCheckStarted(false)
        }

    }

}
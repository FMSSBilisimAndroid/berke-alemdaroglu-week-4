package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ersinberkealemdaroglu.berkealemdarogluweek4.BR
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.adapter.MarsApiAdapter
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.FragmentHomeListBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek4.util.SharedPreferenceManager
import com.ersinberkealemdaroglu.berkealemdarogluweek4.viewmodel.HomeListViewModel

class HomeListFragment : Fragment() {
    private lateinit var binding: FragmentHomeListBinding
    private lateinit var sharedPreferenceManager: SharedPreferenceManager
    private val homeListViewModel: HomeListViewModel by viewModels()
    private var adapter: MarsApiAdapter = MarsApiAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_list, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
    }

    private fun init() {
        context?.let {
            sharedPreferenceManager = SharedPreferenceManager(it)
        }

        refreshData()
        getMarsApiData()
        logOutButton()
    }


    /**
     * homeListViewModel içindeki marsValueden gelen livedataları observe ederek recyclerview içerisine ekliyoruz.
     * recyclerview i gridLayoutManager ile 2 li grid yapıda olmasını sağlıyoruz.
     * BR ile de xml de verilen adapter datasına ulaşarak verileri setliyoruz.
     */
    private fun getMarsApiData() {

        homeListViewModel.getMarsData().observe(viewLifecycleOwner) { marsValue ->
            adapter.setMarsArrayList(marsValue)
            val gridLayoutManager = GridLayoutManager(context, 2)
            binding.apply {
                recyclerview.layoutManager = gridLayoutManager
                setVariable(BR.marsAdapter, adapter)
            }
        }


    }

    /**
     * xml de verdiğimiz swipeRefreshLayout u refresh atıldığında olacak durumları belirliyoruz. HomeListViewModel.getMarsData() ile de veriler tekrar apiden çekiliyor.
     */
    private fun refreshData() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                recyclerview.visibility = View.INVISIBLE
                marsLoading.visibility = View.INVISIBLE
                homeListViewModel.getMarsData()
                swipeRefreshLayout.isRefreshing = false
            }

        }

        /**
         * xml de verdiğim ProgressBar ın durumunu belirliyoruz. Observe ile homeListViewModel de tanımlanan loading boolean değerini alarak gerekli düzenlemeleri sağlıyoruz.
         */
        homeListViewModel.marsLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                binding.apply {
                    if (it) {
                        marsLoading.visibility = View.VISIBLE
                        recyclerview.visibility = View.INVISIBLE
                    } else {
                        recyclerview.visibility = View.VISIBLE
                        marsLoading.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun logOutButton() {
        binding.logOutButton.setOnClickListener {
            sharedPreferenceManager.saveCheckStarted(false)
            findNavController().navigate(R.id.action_homeListFragment_to_getStartedFragment)
        }
    }

}
package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.FragmentGetStartedBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek4.util.SharedPreferenceManager

class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGetStartedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            sharedPreferenceManager = SharedPreferenceManager(it)
        }
        getStartedShared()
    }

    private fun getStartedShared() {

        if (sharedPreferenceManager.getBool()) {
            findNavController().navigate(R.id.action_getStartedFragment_to_homeListFragment)
        } else {
            binding.getStartedButton.setOnClickListener {
                sharedPreferenceManager.setBool(true)
                findNavController().navigate(R.id.action_getStartedFragment_to_homeListFragment)
            }
        }
    }

}
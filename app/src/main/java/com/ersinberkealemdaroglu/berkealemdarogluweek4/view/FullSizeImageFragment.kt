package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.FragmentFullSizeImagaBinding


class FullSizeImageFragment : Fragment() {
    private lateinit var binding: FragmentFullSizeImagaBinding
    private val navArgs: FullSizeImageFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_full_size_imaga, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageFullSize()
    }


    private fun imageFullSize() {
        navArgs.let {
            binding.marsData = it.marsData
        }
    }

}
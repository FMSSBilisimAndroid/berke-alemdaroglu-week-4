package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.FragmentFieldDetailBinding

class FieldDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentFieldDetailBinding
    private val navArgs: FieldDetailFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_field_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMarsDataDetailsByArgs()
        bottomSheetController()
        backButtonController()
    }

    private fun setMarsDataDetailsByArgs() {
        navArgs?.let {
            dataBinding.marsData = it.marsData

        }
    }

    private fun bottomSheetController(){
        val bottomSheetFragment = BottomSheetFragment()
        dataBinding.buyNowButton.setOnClickListener {
            /**
             * TODO ödeme tamamlandıktan sonra ödeme için teşekkür ederiz bölümü etklinleştirilecek.
             */
            bottomSheetFragment.show(childFragmentManager, "tag")
        }
    }

    private fun backButtonController(){
        dataBinding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_fieldDetailFragment_to_homeListFragment2)
        }
    }


}
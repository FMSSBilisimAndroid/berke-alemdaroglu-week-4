package com.ersinberkealemdaroglu.berkealemdarogluweek4.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_started, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        context?.let {
            sharedPreferenceManager = SharedPreferenceManager(it)
        }

        //Text Html
        binding.getStartedTitle.text =
            HtmlCompat.fromHtml(getString(R.string.html), HtmlCompat.FROM_HTML_MODE_LEGACY)

        getStartedShared()
    }

    /**
     * SharedPreference ile getStartedFragmentteki butona kullanıcı bir defa bastıktan sonra her girişte splash ekran gibi 1 saniye gösteriliyor sonra homeListFragmentta geçişi sağlanıyor.
     */
    private fun getStartedShared() {
        if (sharedPreferenceManager.getCheckStarted()) {
            binding.getStartedButton.visibility = View.INVISIBLE
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    findNavController().navigate(R.id.action_getStartedFragment_to_homeListFragment)
                }, 1000)
            }

        } else {
            binding.getStartedButton.setOnClickListener {
                sharedPreferenceManager.saveCheckStarted(true)
                findNavController().navigate(R.id.action_getStartedFragment_to_homeListFragment)
            }
        }
    }

}
package com.ersinberkealemdaroglu.berkealemdarogluweek4.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.RecyclerviewItemRowBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel
import com.ersinberkealemdaroglu.berkealemdarogluweek4.view.HomeListFragmentDirections

class MarsApiViewHolder(
    private val marsBinding: ViewDataBinding,
) : RecyclerView.ViewHolder(marsBinding.root) {
    fun onBind(marsDataModel: MarsDataModel) {
        val binding = marsBinding as RecyclerviewItemRowBinding

        /**
         *  BR ile xmlde tanımlanan marsDataModelimizde ulaşarak marsDataModel ini set ediyoruz.
         */
        binding.apply {
            setVariable(BR.marsDataModel, marsDataModel)

            /**
             * Recyclerview da ki imageye tıklandığında fieldDetailFragmentta yönlendirilmesi yapılıyor.
             * marsDataModel ile verileri de argument ile göndermiş oluyoruz.
             */
            marsImage.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(HomeListFragmentDirections.actionHomeListFragmentToFieldDetailFragment(
                        marsDataModel))
            }
        }
    }


}
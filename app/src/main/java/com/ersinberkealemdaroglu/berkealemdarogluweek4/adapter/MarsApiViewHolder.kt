package com.ersinberkealemdaroglu.berkealemdarogluweek4.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.berkealemdarogluweek4.databinding.RecyclerviewItemRowBinding
import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel
import com.ersinberkealemdaroglu.berkealemdarogluweek4.view.HomeListFragmentDirections

class MarsApiViewHolder(
    val marsBinding: ViewDataBinding,
) : RecyclerView.ViewHolder(marsBinding.root) {
    fun onBind(marsDataModel: MarsDataModel) {
        val binding = marsBinding as RecyclerviewItemRowBinding

        binding.apply {
            setVariable(BR.marsDataModel, marsDataModel)

            marsImage.setOnClickListener {
                val action = HomeListFragmentDirections.actionHomeListFragmentToFieldDetailFragment(
                    marsDataModel)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }


}
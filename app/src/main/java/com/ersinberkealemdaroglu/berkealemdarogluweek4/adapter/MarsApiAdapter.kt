package com.ersinberkealemdaroglu.berkealemdarogluweek4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ersinberkealemdaroglu.berkealemdarogluweek4.R
import com.ersinberkealemdaroglu.berkealemdarogluweek4.model.MarsDataModel

class MarsApiAdapter(
    private val marsArrayList: ArrayList<MarsDataModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val marsBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.recyclerview_item_row, parent, false
        )

        return MarsApiViewHolder(marsBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MarsApiViewHolder).onBind(marsArrayList[position])


    }

    override fun getItemCount(): Int {
        return marsArrayList.size
    }


}
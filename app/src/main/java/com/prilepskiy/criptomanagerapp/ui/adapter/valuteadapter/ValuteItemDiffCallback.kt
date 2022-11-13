package com.prilepskiy.criptomanagerapp.ui.adapter.valuteadapter

import androidx.recyclerview.widget.DiffUtil
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel

class ValuteItemDiffCallback:  DiffUtil.ItemCallback<ValuteModel>(){
    override fun areItemsTheSame(oldItem: ValuteModel, newItem: ValuteModel): Boolean {
        return oldItem.ID==newItem.ID
    }

    override fun areContentsTheSame(oldItem: ValuteModel, newItem: ValuteModel): Boolean {
        return oldItem==newItem
    }
}
package com.prilepskiy.criptomanagerapp.ui.adapter.valuteadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.ItemCoinInfoBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemCoinInfoFavoriteBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemValuteActiveBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemValuteBinding
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinAdapter
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinItemDiffCallback
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewHolder

class ValuteAdapterList(private val onValuteClicked: (valute: ValuteModel)->Unit):
    ListAdapter<ValuteModel, BaseViewHolder<ValuteModel, ViewBinding>>(ValuteItemDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ValuteModel, ViewBinding> {
        val binding = when(viewType){
            VALUTE_ACTIVE -> {
                ItemValuteActiveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            VALUTE_PASSIVE -> {
                ItemValuteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            else -> {throw RuntimeException("Unknown view type: $viewType")}
        }
        return ValuteViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.activate){
            VALUTE_ACTIVE
        }else{
            VALUTE_PASSIVE
        }
    }
    inner class ValuteViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<ValuteModel, ViewBinding>(binding) {
        override fun bind(item: ValuteModel, context: Context) {
            with(binding) {

                when (this) {
                    is ItemValuteBinding ->{
                    tvValuteName.text=item.Name


                    }
                    is ItemValuteActiveBinding ->{
                        tvValuteName.text=item.Name
                    }
                }
            }
        }
    }
    override fun onBindViewHolder(holder: BaseViewHolder<ValuteModel, ViewBinding>, position: Int) {
        with(holder) {
            getItem(position)?.let { item ->
                bind(item, itemView.context)
                itemView.setOnClickListener {
                    onValuteClicked(item)
                }
            }
        }
    }
companion object{
    const val VALUTE_PASSIVE=200
    const val VALUTE_ACTIVE=201

}
    }
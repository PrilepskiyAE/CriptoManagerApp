package com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter

import androidx.recyclerview.widget.DiffUtil
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

class CoinItemDiffCallback: DiffUtil.ItemCallback<CoinInfoModel>(){
    override fun areItemsTheSame(oldItem: CoinInfoModel, newItem: CoinInfoModel): Boolean {
        return oldItem.idCoin == newItem.idCoin
    }

    override fun areContentsTheSame(oldItem: CoinInfoModel, newItem: CoinInfoModel): Boolean {
        return oldItem == newItem
    }
}
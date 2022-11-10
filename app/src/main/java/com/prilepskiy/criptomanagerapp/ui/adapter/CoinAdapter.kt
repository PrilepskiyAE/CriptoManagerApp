package com.prilepskiy.criptomanagerapp.ui.adapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseAdapter
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewHolder

class CoinAdapter ():
    BaseAdapter<ViewBinding, CoinInfoModel, BaseViewHolder<CoinInfoModel, ViewBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CoinInfoModel, ViewBinding> {
        TODO("Not yet implemented")
    }

}
package com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.ItemCoinInfoBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemCoinInfoFavoriteBinding
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewHolder

class CoinAdapter(private val onCoinClicked: (coin:CoinInfoModel)-> Unit,private val onFavoriteClicked: ()-> Unit): ListAdapter<CoinInfoModel,BaseViewHolder<CoinInfoModel,ViewBinding>>(CoinItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CoinInfoModel,ViewBinding> {
        val binding = when(viewType){
            COIN -> {
                ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            COIN_FAVORITE -> {
                ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            else -> {throw RuntimeException("Unknown view type: $viewType")}
        }
        return CoinViewHolder(binding)
    }



    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.favorite){
            COIN_FAVORITE
        }else{
            COIN
        }
    }
    inner class CoinViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<CoinInfoModel, ViewBinding>(binding) {
        override fun bind(item: CoinInfoModel, context: Context) {
            with(binding) {

                when (this) {
                    is ItemCoinInfoBinding ->{

                        Glide.with(context)
                            .load(BASE_IMAGE_URL+item.imageUrl)
                            .placeholder(R.drawable.ic_dashboard_black_24dp)
                            .into(ivLogoCoin)

                        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                        val priceTemplate = context.resources.getString(R.string.price_label)

                        tvSymbols.text = String.format(symbolsTemplate, item.fromSymbol, item.toSymbol)
                        tvPrice.text = String.format(priceTemplate, item.price)
                        tvLastUpdate.text = String.format(lastUpdateTemplate, item.lastUpdate)

                        buttomFavorite.setOnClickListener {
                            onFavoriteClicked()
                        }


                    }
                    is ItemCoinInfoFavoriteBinding->{
                        Glide.with(context)
                            .load(BASE_IMAGE_URL+item.imageUrl)
                            .placeholder(R.drawable.ic_dashboard_black_24dp)
                            .into(ivLogoCoin)

                        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                        val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                        val priceTemplate = context.resources.getString(R.string.price_label)

                        tvSymbols.text = String.format(symbolsTemplate, item.fromSymbol, item.toSymbol)
                        tvPrice.text = String.format(priceTemplate, item.price)
                        tvLastUpdate.text = String.format(lastUpdateTemplate, item.lastUpdate)
                        buttomFavorite.setOnClickListener {
                            onFavoriteClicked()
                        }

                    }
                }
            }
        }
    }

    companion object{
        const val COIN=100
        const val COIN_FAVORITE=101
        const val BASE_IMAGE_URL = "https://cryptocompare.com"

    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<CoinInfoModel, ViewBinding>,
        position: Int
    ) {
        with(holder) {
            getItem(position)?.let { item ->
                bind(item, itemView.context)
                itemView.setOnClickListener {
                    onCoinClicked(item)
                }
            }
        }
    }


}
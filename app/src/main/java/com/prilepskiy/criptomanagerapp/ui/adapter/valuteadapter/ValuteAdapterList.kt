package com.prilepskiy.criptomanagerapp.ui.adapter.valuteadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemValuteActiveBinding
import com.prilepskiy.criptomanagerapp.databinding.ItemValuteBinding
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewHolder

class ValuteAdapterList(private val onValuteClicked: (valute: ValuteModel)->Unit,private var valute :ValuteModel?):
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
        return if (item.activate || item.equals(valute)){
            VALUTE_ACTIVE
        }else{
            VALUTE_PASSIVE
        }
    }
    inner class ValuteViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<ValuteModel, ViewBinding>(binding) {
        override fun bind(item: ValuteModel, context: Context) {

            if (valute!=null){
                valute= valute?.copy(activate = false)
            }



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
                    if (valute?.equals(item) == false)
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
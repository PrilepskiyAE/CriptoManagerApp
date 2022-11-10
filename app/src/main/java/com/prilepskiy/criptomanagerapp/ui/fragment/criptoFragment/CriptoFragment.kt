package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentConvertorBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentCriptoBinding
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinAdapter
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment.ConvertorViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoInfoFragment.CriptoInfoFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class CriptoFragment : FragmentBaseNCMVVM<CriptoViewModel, FragmentCriptoBinding>() {
    override val binding: FragmentCriptoBinding by viewBinding()
    override val viewModel: CriptoViewModel by viewModel()

    override fun onEach() {
        val listAdapter:CoinAdapter= CoinAdapter({
                  navigateFragment(CriptoFragmentDirections.actionNavigationCriptoToCriptoInfoFragment(it))
        },{})
        viewModel.getCoinList()
        binding.listCoin.adapter=listAdapter
        onEach(viewModel.coinList){
            Log.d("CriptoViewModel", "onEach: $it")
            listAdapter.submitList(it)
        }
    }
    override fun onView() {

    }

}
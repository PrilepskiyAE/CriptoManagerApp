package com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentHomeBinding
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinAdapter
import com.prilepskiy.criptomanagerapp.ui.adapter.valuteadapter.ValuteAdapterList
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment.CriptoFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : FragmentBaseNCMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val binding: FragmentHomeBinding by viewBinding()
    override val viewModel: HomeViewModel by viewModel()
    override fun onEach() {
        val listAdapter: CoinAdapter = CoinAdapter({
            navigateFragment(HomeFragmentDirections.actionNavigationHomeToCriptoInfoFragment(it))
        },{
            Toast.makeText(requireContext(), "Functionality in development", Toast.LENGTH_SHORT).show()
        })
        val valuteListAdapter:ValuteAdapterList=ValuteAdapterList ({
            navigateFragment(HomeFragmentDirections.actionNavigationHomeToValuteFragment(it))
        }, ValuteModel(
            "RU",
            "R1111111",
            "российский рубль",
            1,
            "099",
            1.00,
            1.00,
            activate = false)
        )
        binding.listCoin.adapter=listAdapter
        binding.listValute.adapter=valuteListAdapter
        onEach(viewModel.coinList){
            binding.progressBar.visibility=View.VISIBLE
            listAdapter.submitList(it)
            if (it != null) {
                if (it.isNotEmpty())
                    binding.progressBar.visibility=View.GONE
            }
        }
        onEach(viewModel.valuteList){
            binding.progressBar2.visibility=View.VISIBLE
            valuteListAdapter.submitList(it)
            if (it != null) {
                if (it.isNotEmpty())
                    Log.d(TAG, "onEach: ")
                    binding.progressBar2.visibility=View.GONE
            }
        }
    }

    override fun onView() {
        viewModel.getValuteList()
        viewModel.getCoinList()
    }
    companion object{
        const val TAG:String="HomeFragment"
    }

}
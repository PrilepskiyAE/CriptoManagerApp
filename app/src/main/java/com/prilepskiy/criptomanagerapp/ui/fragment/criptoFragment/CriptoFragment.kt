package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
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
    val listAdapter:CoinAdapter= CoinAdapter({
        navigateFragment(CriptoFragmentDirections.actionNavigationCriptoToCriptoInfoFragment(it))
    },{
        viewModel.coinList.value?.let { it1 -> viewModel.likeDislike(it, it1,true) }

        //Toast.makeText(requireContext(), "Functionality in development", Toast.LENGTH_SHORT).show()
    })
    override fun onEach() {



        onEach(viewModel.coinList){
            listAdapter.submitList(it)
            if (!it.isNullOrEmpty()) {
                    binding.progressBar2.visibility=View.GONE
                    viewModel.getCoinFavorite()

            }

        }

        onEach(viewModel.searchList){
            viewModel.isLike()
        }

    }

    override fun onView() {
        super.onView()
        binding.listCoin.adapter=listAdapter


    }


}
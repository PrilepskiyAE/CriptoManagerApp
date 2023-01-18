package com.prilepskiy.criptomanagerapp.ui.fragment.criptoInfoFragment

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentCriptoInfoBinding
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinAdapter
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CriptoInfoFragment : FragmentBaseNCMVVM<CriptoInfoFragmentViewModel, FragmentCriptoInfoBinding>() {
    override val binding: FragmentCriptoInfoBinding by viewBinding()
    override val viewModel: CriptoInfoFragmentViewModel by viewModel()
    val args:CriptoInfoFragmentArgs by navArgs()
    override fun onView() {
        with(binding){
            val minPriceTemplate = requireContext().resources.getString(R.string.min_price_label)
            val maxPriceTemplate = requireContext().resources.getString(R.string.max_price_label)
            val priceTemplate = requireContext().resources.getString(R.string.price_label)
            val algTemplate = requireContext().resources.getString(R.string.algorithm_label)


            tvFullName.text=args.cripto.fullName
            tvAlgorithm.text=String.format(algTemplate, args.cripto.algorithm)
            tvPrice.text=String.format(priceTemplate, args.cripto.price)
            tvLowday.text=String.format(minPriceTemplate, args.cripto.lowday)
            tvHighDay.text=String.format(maxPriceTemplate, args.cripto.highday)
            Glide.with(requireContext())
                .load(CoinAdapter.BASE_IMAGE_URL +args.cripto.imageUrl)
                .placeholder(R.drawable.ic_dashboard_black_24dp)
                .into(binding.imgLogo)
        }
    }



}
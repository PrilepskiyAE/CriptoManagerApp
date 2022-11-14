package com.prilepskiy.criptomanagerapp.ui.fragment.valuteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentHomeBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentValuteBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoInfoFragment.CriptoInfoFragmentArgs
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ValuteFragment : FragmentBaseNCMVVM<ValuteViewModel, FragmentValuteBinding>() {
    override val binding: FragmentValuteBinding by viewBinding()
    override val viewModel: ValuteViewModel by viewModel()
    val args: ValuteFragmentArgs by navArgs()


    override fun onView() {
        with(binding) {
            val charCodeTemplate = requireContext().resources.getString(R.string.charcode_string)
            val nameTemplate = requireContext().resources.getString(R.string.name_string)
            val valueTemplate = requireContext().resources.getString(R.string.value_string)
            val nominalTemplate = requireContext().resources.getString(R.string.nominal_string)
            val previousTemplate = requireContext().resources.getString(R.string.previous_string)
            val numCodeTemplate = requireContext().resources.getString(R.string.numcode_sring)


            tvCharCode.text = String.format(charCodeTemplate, args.valute.CharCode)
            tvName.text = String.format(nameTemplate, args.valute.Name)
            tvValue.text = String.format(valueTemplate, args.valute.Value.toString())
            tvNominal.text = String.format(nominalTemplate, args.valute.Nominal.toString())
            tvPrevious.text = String.format(previousTemplate, args.valute.Previous.toString())
            tvNumCode.text = String.format(numCodeTemplate, args.valute.NumCode)
        }
    }

}
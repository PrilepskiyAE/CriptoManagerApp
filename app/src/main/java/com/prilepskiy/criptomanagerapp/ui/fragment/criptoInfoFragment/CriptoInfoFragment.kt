package com.prilepskiy.criptomanagerapp.ui.fragment.criptoInfoFragment

import com.prilepskiy.criptomanagerapp.databinding.FragmentCriptoInfoBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class CriptoInfoFragment : FragmentBaseNCMVVM<CriptoInfoFragmentViewModel, FragmentCriptoInfoBinding>() {
    override val binding: FragmentCriptoInfoBinding by viewBinding()
    override val viewModel: CriptoInfoFragmentViewModel by viewModel()

}
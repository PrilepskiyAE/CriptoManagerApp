package com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentConvertorBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentHomeBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConvertorFragment : FragmentBaseNCMVVM<ConvertorViewModel, FragmentConvertorBinding>() {
    override val binding: FragmentConvertorBinding by viewBinding()
    override val viewModel: ConvertorViewModel by viewModel()

}
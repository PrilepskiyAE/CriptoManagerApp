package com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentConvertorBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentHomeBinding
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.dialog.ValuteDialog
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeFragment
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConvertorFragment : FragmentBaseNCMVVM<ConvertorViewModel, FragmentConvertorBinding>() {
    override val binding: FragmentConvertorBinding by viewBinding()
    override val viewModel: ConvertorViewModel by viewModel()
    private var valuteDialog: ValuteDialog? = null
    private var valuteRight:ValuteModel?=null
    private var valuteLeft:ValuteModel?=null
    override fun onEach() {
        viewModel.getValuteList()
        onEach(viewModel.valuteList){
            if (it != null) {
                if (it.isNotEmpty())
                    Log.d(HomeFragment.TAG, "onEach: ")
                binding.progressBar.visibility=View.GONE
            }
        }
    }
    override fun onViewClick() {
        binding.tvUpdateLeft.setOnClickListener {
            showValuteDialog(true)
        }
        binding.tvUpdateRight.setOnClickListener {
            showValuteDialog(false)
        }
    }

    fun showValuteDialog(mod:Boolean){
        if (valuteDialog == null)
            valuteDialog= ValuteDialog ({
                if (mod) {
                    valuteLeft = it
                    valuteRight = null
                    binding.tvValute1.text=valuteLeft?.CharCode
                } else {
                    valuteRight = it
                    valuteLeft = null
                    binding.tvValute2.text=valuteRight?.CharCode
                }
            },valuteLeft,valuteRight)
        valuteDialog?.onDismissAction = {
            valuteDialog =null
        }
        valuteDialog?.onCancelAction = {
            valuteDialog =null
        }
        try {
            if (!valuteDialog!!.isVisible && !valuteDialog!!.isAdded)
                valuteDialog!!.show(childFragmentManager, "ERROR_DIALOG_TAG")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onView() {
        viewModel.getValuteList()
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: right -> $valuteRight left ->$valuteLeft")

    }

}
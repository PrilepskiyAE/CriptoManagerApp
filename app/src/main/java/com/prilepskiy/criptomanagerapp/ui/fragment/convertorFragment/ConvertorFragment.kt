package com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment

import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.prilepskiy.criptomanagerapp.databinding.FragmentConvertorBinding
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.dialog.ValuteDialog
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConvertorFragment : FragmentBaseNCMVVM<ConvertorViewModel, FragmentConvertorBinding>() {
    override val binding: FragmentConvertorBinding by viewBinding()
    override val viewModel: ConvertorViewModel by viewModel()
    private var valuteDialog: ValuteDialog? = null

    override fun onEach() {
        viewModel.getValuteList()
        onEach(viewModel.valuteList) {
            if (it != null) {
                if (it.isNotEmpty())
                binding.progressBar.visibility = View.GONE
            }
        }
        onEach(viewModel.valuteLeft) {
            binding.tvValute1.text = it.CharCode

        }
        onEach(viewModel.valuteRight) {
            binding.tvValute2.text = it.CharCode
        }

        onEach(viewModel.valuteStringLeft) {
            if (viewModel.valuteRight.value.equals(viewModel.valuteLeft.value)) {
                binding.editTextNumberDecimal2.setText(it)
                if (!it.isNullOrEmpty())
                    binding.editTextNumberDecimal2.setSelection(it.count())
            } else {
                try {


                    val ed = viewModel.valuteLeft.value.Value / viewModel.valuteLeft.value.Nominal
                    val v = (ed * it.toDouble()) / viewModel.valuteRight.value.Value
                    if (!v.toString().isNullOrEmpty())
                        binding.editTextNumberDecimal2.setText(String.format("%.5f", v))
                    if (!it.isNullOrEmpty())
                        binding.editTextNumberDecimal2.setSelection(String.format("%.5f", v).count())
                    if (it.isEmpty())
                        binding.editTextNumberDecimal2.setText("")


                }catch (e:NumberFormatException){
clear()
                }

            }

        }
        onEach(viewModel.valuteStringRight) {
            if (viewModel.valuteRight.value.equals(viewModel.valuteLeft.value)) {
                binding.editTextNumberDecimal.setText(it)
                if (!it.isNullOrEmpty())
                    binding.editTextNumberDecimal.setSelection(viewModel.valuteStringLeft.value.count())
            } else {
                try {
                val ed=viewModel.valuteRight.value.Value/viewModel.valuteRight.value.Nominal
                val v=(ed*it.toDouble())/viewModel.valuteLeft.value.Value
                if (!v.toString().isNullOrEmpty())
                binding.editTextNumberDecimal.setText(String.format("%.5f", v))
                    if (!it.isNullOrEmpty())
                        binding.editTextNumberDecimal.setSelection(String.format("%.5f", v).count())
                    if (it.isEmpty())
                        binding.editTextNumberDecimal.setText("")

                }
                catch (e:NumberFormatException){
clear()
                }

        }


    }
    }

    override fun onViewClick() {
        binding.tvUpdateLeft.setOnClickListener {
           clear()
            showValuteDialog(true, viewModel.valuteRight.value)
        }
        binding.tvUpdateRight.setOnClickListener {
            clear()
            showValuteDialog(false, viewModel.valuteLeft.value)

        }
    }
fun clear(){
   binding.editTextNumberDecimal.setText("")
    binding.editTextNumberDecimal2.setText("")
   viewModel.getStringRight("")
   viewModel.getStringLeft("")
}
    override fun onView() {
        viewModel.getValuteList()

        binding.editTextNumberDecimal.setOnFocusChangeListener { view, b ->

           clear()
            binding.editTextNumberDecimal.doOnTextChanged { text, start, before, count ->

                viewModel.getStringLeft(text.toString())

            }
        }

        binding.editTextNumberDecimal2.setOnFocusChangeListener { view, b ->
            clear()
            binding.editTextNumberDecimal2.doOnTextChanged { text, start, before, count ->
                viewModel.getStringRight(text.toString())

            }
        }


    }

    fun showValuteDialog(isLeftOrRight: Boolean, valute: ValuteModel?) {
        if (valuteDialog == null)
            valuteDialog = ValuteDialog({
                if (isLeftOrRight) {
                    viewModel.updateLeft(it)
                } else {
                    viewModel.updateRight(it)
                }
            }, valute)
        valuteDialog?.onDismissAction = {
            valuteDialog = null
        }
        valuteDialog?.onCancelAction = {
            valuteDialog = null
        }
        try {
            if (!valuteDialog!!.isVisible && !valuteDialog!!.isAdded)
                valuteDialog!!.show(childFragmentManager, "ERROR_DIALOG_TAG")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val TAG = "ConvertorFragment"
    }
}
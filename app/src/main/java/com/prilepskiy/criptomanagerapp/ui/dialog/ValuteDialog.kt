package com.prilepskiy.criptomanagerapp.ui.dialog

import android.content.DialogInterface
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.ValuteDialogBinding
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.adapter.valuteadapter.ValuteAdapterList
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment.ConvertorViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ValuteDialog(
    private val click: (ValuteModel) -> Unit,
    private val valuteLeft:ValuteModel?,
    private val valuteRight:ValuteModel?
    ): BaseDialog<ValuteDialogBinding>() {
    override val dialogStyle: Int
        get() = R.style.AppTheme_FullScreenDialog
    override val binding: ValuteDialogBinding by viewBinding()
    val viewModel: ConvertorViewModel by viewModel()
    var onDismissAction: () -> Unit = {}
    var onCancelAction: () -> Unit = {}
    val valuteListAdapter: ValuteAdapterList = ValuteAdapterList {
        click(it)
        viewModel.updateList(it)
        dismiss()

    }

    override fun onEach() {
        binding.rcValute.adapter=valuteListAdapter
        onEach(viewModel.valuteList){
            Log.d("TAG", "valuteLeft: $valuteLeft")
            Log.d("TAG", "valuteRight: $valuteRight")
            valuteListAdapter.submitList(it)
        }
    }

    override fun onView() {
        viewModel.getValuteList()

    }
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissAction()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancelAction()
    }
}
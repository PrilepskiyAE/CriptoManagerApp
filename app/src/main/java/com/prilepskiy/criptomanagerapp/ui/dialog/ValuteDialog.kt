package com.prilepskiy.criptomanagerapp.ui.dialog

import android.content.DialogInterface
import androidx.fragment.app.DialogFragment
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.ValuteDialogBinding
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding

class ValuteDialog: BaseDialog<ValuteDialogBinding>() {
    override val dialogStyle: Int
        get() = R.style.AppTheme_FullScreenDialog
    override val binding: ValuteDialogBinding by viewBinding()
    var onDismissAction: () -> Unit = {}
    var onCancelAction: () -> Unit = {}


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissAction()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onCancelAction()
    }
}
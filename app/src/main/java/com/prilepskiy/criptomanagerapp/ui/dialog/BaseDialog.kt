package com.prilepskiy.criptomanagerapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<ViewBind : ViewBinding> :
    DialogFragment() {
    abstract val binding: ViewBind
    protected abstract val dialogStyle: Int?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogStyle?.let { setStyle(STYLE_NO_TITLE, it) };
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onEach()
        onView()
        onViewClick()
    }

    protected open fun onView() {}

    protected open fun onViewClick() {}

    protected open fun onEach() {}
}
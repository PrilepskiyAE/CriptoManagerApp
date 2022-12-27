package com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileLoginBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileLoginFragment : FragmentBaseNCMVVM<ProfileLoginViewModel, FragmentProfileLoginBinding>() {
    override val binding: FragmentProfileLoginBinding by viewBinding()
    override val viewModel:ProfileLoginViewModel by viewModel()
    override fun onViewClick() {
        binding.btLogin.setOnClickListener {
            viewModel.login("test")
            navigateFragment(ProfileLoginFragmentDirections.actionProfileLoginFragmentToProfileAuthorizationFragment())
        }
    }

}
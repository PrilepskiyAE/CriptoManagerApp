package com.prilepskiy.criptomanagerapp.ui.fragment.profileAuthorizationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileAuthorizationBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileFragmentDirections
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileAuthorizationFragment : FragmentBaseNCMVVM<ProfileAuthorizationViewModel, FragmentProfileAuthorizationBinding>() {
    override val binding: FragmentProfileAuthorizationBinding by viewBinding()
    override val viewModel: ProfileAuthorizationViewModel by viewModel()
    override fun onViewClick() {
        binding.btLogout.setOnClickListener {
            viewModel.logout()
            navigateFragment(R.id.navigation_home)
        }
    }
    override fun onEach() {
        onEach(viewModel.navigateLogin){
            if (it == false){
                navigateFragment(ProfileAuthorizationFragmentDirections.actionProfileAuthorizationFragmentToProfileLoginFragment())
            }
        }
    }
    override fun onView() {
        viewModel.isAutorization()
    }
}
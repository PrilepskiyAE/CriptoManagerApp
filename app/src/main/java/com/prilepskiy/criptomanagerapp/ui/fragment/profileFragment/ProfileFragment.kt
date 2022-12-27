package com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentHomeBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileBinding
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : FragmentBaseNCMVVM<ProfileViewModel, FragmentProfileBinding>() {
    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModel()
    override fun onEach() {
        onEach(viewModel.navigateLogin){
            Log.d("TAG", "onEach: $it")
            if (it == true){
                navigateFragment(ProfileFragmentDirections.actionNavigationProfileToProfileAuthorizationFragment())
            }else{
               navigateFragment(ProfileFragmentDirections.actionNavigationProfileToProfileLoginFragment())
            }
        }
    }

    override fun onView() {
        super.onView()
        viewModel.isAutorization()
    }

}

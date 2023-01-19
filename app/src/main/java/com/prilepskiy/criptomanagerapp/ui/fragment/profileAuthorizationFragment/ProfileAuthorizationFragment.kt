package com.prilepskiy.criptomanagerapp.ui.fragment.profileAuthorizationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilepskiy.criptomanagerapp.R
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileAuthorizationBinding
import com.prilepskiy.criptomanagerapp.databinding.FragmentProfileBinding
import com.prilepskiy.criptomanagerapp.ui.adapter.coinadapter.CoinAdapter
import com.prilepskiy.criptomanagerapp.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.criptomanagerapp.ui.base.viewBinding
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment.CriptoFragmentDirections
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileFragmentDirections
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileAuthorizationFragment : FragmentBaseNCMVVM<ProfileAuthorizationViewModel, FragmentProfileAuthorizationBinding>() {
    override val binding: FragmentProfileAuthorizationBinding by viewBinding()
    override val viewModel: ProfileAuthorizationViewModel by viewModel()

    val listAdapter: CoinAdapter = CoinAdapter({
            navigateFragment(ProfileAuthorizationFragmentDirections.actionProfileAuthorizationFragmentToCriptoInfoFragment(it))
    },{
        viewModel.coinList.value?.let { it1 -> viewModel.likeDislike(it, it1,true) }

        //Toast.makeText(requireContext(), "Functionality in development", Toast.LENGTH_SHORT).show()
    })
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

        onEach(viewModel.coinList){


            listAdapter.submitList(it)
            if (!it.isNullOrEmpty()) {
                viewModel.getCoinFavorite()

            }

        }

        onEach(viewModel.searchList){
            viewModel.isLike()
        }
        onEach(viewModel.userList){
          binding.tvUserName.text= it?.get(0)?.username
          binding.tvEmail.text= it?.get(0)?.email
        }
    }
    override fun onView() {
        viewModel.isAutorization()
        binding.listCoin.adapter=listAdapter
        binding.tvUserName.text
    }
}
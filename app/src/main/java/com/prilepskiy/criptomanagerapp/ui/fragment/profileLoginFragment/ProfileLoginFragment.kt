package com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment

import android.os.Bundle
import android.util.Log
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
    private var isReg:Boolean=false
    override fun onViewClick() {
        binding.btLogin.setOnClickListener {
            if (!isReg){
                viewModel.searchUser(binding.etLogin.text.toString())
           // navigateFragment(ProfileLoginFragmentDirections.actionProfileLoginFragmentToProfileAuthorizationFragment())
            }else
            {
                viewModel.searchUser(binding.etLogin.text.toString())


            }
        }
        binding.btReg.setOnClickListener {
            binding.etEmail.visibility=View.VISIBLE
            binding.btReg.visibility=View.GONE
            isReg=true
            binding.tvLogin.text=getString(R.string.reg_btn)

        }
    }

    override fun onEach() {
       onEach(viewModel.searchUser){
           if (it?.isEmpty() == true){

               if (isReg) {
                   binding.HintError.visibility = View.GONE
                   viewModel.addUser(
                       binding.etLogin.text.toString(),
                       binding.etPass.text.toString(),
                       binding.etEmail.text.toString()
                   )
                   viewModel.login(binding.etLogin.text.toString())
                   navigateFragment(ProfileLoginFragmentDirections.actionProfileLoginFragmentToProfileAuthorizationFragment())
               }
           }else{
               if (isReg) {
               binding.HintError.visibility=View.VISIBLE
               }else{
                   if ( it?.get(0)?.username==binding.etLogin.text.toString() && it[0].password ==binding.etPass.text.toString() ){
                       viewModel.login(binding.etLogin.text.toString())
                       navigateFragment(ProfileLoginFragmentDirections.actionProfileLoginFragmentToProfileAuthorizationFragment())
                   }else{
                       binding.HintError.visibility=View.VISIBLE
                   }

               }

           }
       }

    }

}
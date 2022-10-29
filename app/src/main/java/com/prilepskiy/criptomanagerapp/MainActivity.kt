package com.prilepskiy.criptomanagerapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.prilepskiy.criptomanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_cripto,R.id.navigation_convertor,R.id.navigation_profile
            )
        )
             navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)


        navView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.navigation_home -> {
                    navController.popBackStack(R.id.navigation_home,false, saveState = true)
                }

                R.id.navigation_cripto -> {
                    navController.navigate(R.id.navigation_cripto, null)
                }


                R.id.navigation_convertor -> {
                    navController.navigate(R.id.navigation_convertor, null)
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile, null)
                }
            }
            true
        }
    }

    }

package com.me.mvi.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.me.mvi.demo.databinding.ActivityMainBinding
import com.yunzhu.frame.base.BaseBindingActivity
import com.yunzhu.frame.extensions.setupWithNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun init() {
        setupBottomNavigationBar()
    }

    override fun setListener() {

    }

    private fun setupBottomNavigationBar() {
        val navGraphIds =
            listOf(R.navigation.navi_home, R.navigation.navi_project, R.navigation.navi_personal)

        val controller = binding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        controller?.observe(this, Observer { navController ->
            //setupActionBarWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
//                run {
//                    val id = destination.id
//                    if (id == R.id.projectContentFragment) {
//                        binding?.navView?.visibility = View.GONE
//                    } else {
//                        binding?.navView?.visibility = View.VISIBLE
//                    }
//                }
            }
        })
    }
}

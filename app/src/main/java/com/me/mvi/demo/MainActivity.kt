package com.me.mvi.demo


import androidx.lifecycle.Observer
import com.me.mvi.demo.databinding.ActivityMainBinding
import com.yunzhu.frame.base.BaseBindingActivity
import com.yunzhu.frame.extensions.setupWithNavController

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

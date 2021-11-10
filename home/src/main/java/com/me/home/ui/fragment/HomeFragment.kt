package com.me.home.ui.fragment

import com.google.android.material.tabs.TabLayoutMediator
import com.me.home.databinding.FragmentHomeBinding
import com.me.home.ui.adapter.HomePageAdapter
import com.yunzhu.frame.base.BaseBindingFragment

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {

    private lateinit var homePageAdapter: HomePageAdapter

    override fun initView() {
        homePageAdapter = HomePageAdapter(this)
        binding.vpHome.adapter = homePageAdapter

        binding.run {
            TabLayoutMediator(homeTabLayout, vpHome) { tab, position ->
                when (position) {
                    0 -> tab.text = "每日一问"
                    1 -> tab.text = "首页"
                    2 -> tab.text = "广场"
                }
            }.attach()
        }
    }

    override fun setListener() {

    }

    override fun invalidate() {

    }



}
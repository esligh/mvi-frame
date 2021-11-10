package com.me.home.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.me.home.ui.fragment.ArticleFragment
import com.me.home.ui.fragment.DailyQuestionFragment
import com.me.home.ui.fragment.SquareFragment

class HomePageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DailyQuestionFragment()
            1 -> ArticleFragment()
            else -> SquareFragment()
        }
    }
}
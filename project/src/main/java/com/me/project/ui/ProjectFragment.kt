package com.me.project.ui
import com.me.project.databinding.FragmentProjectBinding
import com.me.project.views.basicRow
import com.me.project.views.marquee
import com.yunzhu.frame.base.BaseBindingFragment

class ProjectFragment : BaseBindingFragment<FragmentProjectBinding>() {

    override fun initView() {
        binding.recyclerView.withModels {

            marquee {
                id("marquee")
                title("Intro")
                subtitle("Set the initial counter value")
            }

            arrayOf(0, 10, 50, 100, 1_000, 10_000).forEach { count ->
                basicRow {
                    id(count)
                    title("$count")
                    subtitle("a:$count")
                    clickListener { _ ->

                    }
                }
            }
        }
    }

    override fun setListener() {

    }

}
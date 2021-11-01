package com.me.mvi.demo

import com.airbnb.mvrx.activityViewModel
import com.me.mvi.demo.databinding.FragmentFlowIntroBinding
import com.me.mvi.demo.views.MarqueeModel_
import com.me.mvi.demo.views.basicRow
import com.me.mvi.demo.views.marquee
import com.me.mvi.demo.vm.FlowViewModel
import com.yunzhu.frame.base.BaseBindingFragment

class FlowIntroFragment : BaseBindingFragment<FragmentFlowIntroBinding>() {
    private val viewModel: FlowViewModel by activityViewModel()

    override fun initView() {
        //        binding.toolbar.setupWithNavController(findNavController())
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
                    clickListener { _ ->
                        viewModel.setCount(count)
                    }
                }
            }

            MarqueeModel_().title("111").subtitle("222")
        }
    }

    override fun setListener() {

    }

    override fun invalidate() {

    }

}

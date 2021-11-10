package com.me.home.ui.fragment

import androidx.lifecycle.lifecycleScope
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.activityViewModel
import com.me.home.databinding.FragmentDailyQuestionBinding
import com.me.home.ui.adapter.DailyQuestionPagingAdapter
import com.me.home.vm.DailyQuestionViewModel
import com.yunzhu.frame.base.BaseBindingFragment
import com.yunzhu.frame.widget.FooterAdapter
import kotlinx.coroutines.flow.collectLatest

class DailyQuestionFragment : BaseBindingFragment<FragmentDailyQuestionBinding>() {

    private val mViewModel: DailyQuestionViewModel by activityViewModel()

    private val dailyPagingAdapter = DailyQuestionPagingAdapter()

    override fun initView() {
        binding.rvDailyQuestion?.adapter = dailyPagingAdapter.withLoadStateFooter(
            FooterAdapter {
                dailyPagingAdapter.retry()
            })
    }

    override fun setListener() {
        lifecycleScope.launchWhenCreated {
            mViewModel.dailyQuestionPagingFlow().collectLatest {
                dailyPagingAdapter.submitData(it)
            }
        }
    }

    override fun invalidate() {

    }

}
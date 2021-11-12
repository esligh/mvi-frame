package com.me.home.ui.fragment

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.activityViewModel
import com.me.home.databinding.FragmentSquareBinding
import com.me.home.ui.adapter.SquarePagingAdapter
import com.me.home.vm.HomeViewModel
import com.yunzhu.frame.base.BaseBindingFragment
import kotlinx.coroutines.flow.collectLatest


class SquareFragment : BaseBindingFragment<FragmentSquareBinding>() {

    private val mViewModel: HomeViewModel by activityViewModel()

    override fun initView() {
        val pagingAdapter = SquarePagingAdapter()
        binding.rvSquare.adapter = pagingAdapter

        lifecycleScope.launchWhenCreated {
            mViewModel.squarePagingFlow().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        //下拉刷新
        binding?.swipeLayout?.setOnRefreshListener { pagingAdapter.refresh() }
        lifecycleScope.launchWhenCreated {
            pagingAdapter.loadStateFlow.collectLatest {
                //根据Paging的请求状态收缩刷新view
                binding?.swipeLayout?.isRefreshing = it.refresh is LoadState.Loading
            }
        }
    }

    override fun setListener() {


    }


}
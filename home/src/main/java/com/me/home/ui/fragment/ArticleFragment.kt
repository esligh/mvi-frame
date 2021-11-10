package com.me.home.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.me.home.databinding.FragmentArticleBinding
import com.me.home.ui.adapter.ArticleMultiPagingAdapter
import com.me.home.vm.ArticleViewModel
import com.yunzhu.frame.base.BaseBindingFragment
import com.yunzhu.frame.widget.FooterAdapter
import kotlinx.coroutines.flow.collectLatest

data class ArticleState(@PersistState val count: Int = 0) : MavericksState

class ArticleFragment : BaseBindingFragment<FragmentArticleBinding>() {

    private val mViewModel: ArticleViewModel by activityViewModel()
    private var mArticlePagingAdapter = ArticleMultiPagingAdapter()

    @ExperimentalPagingApi
    override fun initView() {
        binding?.rvHomeArticle?.adapter = mArticlePagingAdapter.withLoadStateFooter(
            FooterAdapter {
                //重新请求
                mArticlePagingAdapter.retry()
            })

        mViewModel.loadBanner()
        mViewModel.bannerLiveData.observe(this, Observer {
            if(it.isSuccess){
                it.data?.let {
                    mArticlePagingAdapter.addBannerList(it)
                    mArticlePagingAdapter.notifyItemChanged(0)
                }
            }
        })

        lifecycleScope.launchWhenCreated {
            mViewModel.articlePagingFlow().collectLatest { data ->
                mArticlePagingAdapter.submitData(data)
            }
        }
    }

    override fun setListener() {

    }

    override fun invalidate() = withState(mViewModel) { state ->

    }

}
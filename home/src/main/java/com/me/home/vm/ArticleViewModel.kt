package com.me.home.vm

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.me.home.ui.fragment.ArticleState
import com.me.repo.base.StateLiveData
import com.me.repo.entity.home.ArticleData
import com.me.repo.entity.home.BannerData
import com.me.repo.module.HomeRepo
import com.yunzhu.frame.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ArticleViewModel(state: ArticleState,private val repo: HomeRepo): BaseViewModel<ArticleState>(state) {

    companion object : MavericksViewModelFactory<ArticleViewModel, ArticleState> {

        override fun create(viewModelContext: ViewModelContext, state: ArticleState): ArticleViewModel {
            val repo: HomeRepo by viewModelContext.activity.inject()
            return ArticleViewModel(state, repo)
        }
    }

    val bannerLiveData = StateLiveData<List<BannerData>>()

    fun loadBanner() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBanner(bannerLiveData)
        }
    }

    /**
     * 请求首页文章数据
     */
    @ExperimentalPagingApi
    fun articlePagingFlow(): Flow<PagingData<ArticleData>> =
        repo.getHomeArticle(1).cachedIn(viewModelScope)

}
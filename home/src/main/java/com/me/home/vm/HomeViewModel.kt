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
import com.me.repo.entity.home.DailyQuestionData
import com.me.repo.entity.home.SquareData
import com.me.repo.module.HomeRepo
import com.yunzhu.frame.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeViewModel(state: ArticleState, private val repo: HomeRepo): BaseViewModel<ArticleState>(state) {

    companion object : MavericksViewModelFactory<HomeViewModel, ArticleState> {

        override fun create(viewModelContext: ViewModelContext, state: ArticleState): HomeViewModel {
            val repo: HomeRepo by viewModelContext.activity.inject()
            return HomeViewModel(state, repo)
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


    /**
     * 请求每日一问数据
     */
    fun dailyQuestionPagingFlow(): Flow<PagingData<DailyQuestionData>> =
            repo.getDailyQuestion().cachedIn(viewModelScope)

    /**
     * 查询广场数据
     */
    fun squarePagingFlow(): Flow<PagingData<SquareData>> =
            repo.getSquareData().cachedIn(viewModelScope)

}
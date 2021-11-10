package com.me.home.vm

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.me.repo.entity.home.DailyQuestionData
import com.me.repo.module.HomeRepo
import com.yunzhu.frame.base.BaseViewModel
import com.yunzhu.frame.base.NoState
import kotlinx.coroutines.flow.Flow
import org.koin.android.ext.android.inject

class DailyQuestionViewModel(state: NoState, private val repo: HomeRepo): BaseViewModel<NoState>(state) {

    companion object : MavericksViewModelFactory<DailyQuestionViewModel, NoState> {

        override fun create(viewModelContext: ViewModelContext, state: NoState): DailyQuestionViewModel {
            val repo: HomeRepo by viewModelContext.activity.inject()
            return DailyQuestionViewModel(state, repo)
        }
    }


    /**
     * 请求每日一问数据
     */
    fun dailyQuestionPagingFlow(): Flow<PagingData<DailyQuestionData>> =
        repo.getDailyQuestion().cachedIn(viewModelScope)



}
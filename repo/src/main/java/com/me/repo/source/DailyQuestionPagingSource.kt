package com.me.repo.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.me.repo.api.HomeService
import com.me.repo.entity.home.DailyQuestionData

/**
 * 每日一问数据源，主要配合Paging3进行数据请求与显示
 */
class DailyQuestionPagingSource(private val service: HomeService) : PagingSource<Int, DailyQuestionData>() {
    override fun getRefreshKey(state: PagingState<Int, DailyQuestionData>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DailyQuestionData> {
        return try {
            val pageNum = params.key ?: 1
            val data = service.getDailyQuestion(pageNum)
            val preKey = if (pageNum > 1) pageNum - 1 else null
            LoadResult.Page(data.data?.datas!!, prevKey = preKey, nextKey = pageNum + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
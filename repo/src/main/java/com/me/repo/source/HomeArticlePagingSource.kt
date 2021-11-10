package com.me.repo.source
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.me.repo.api.HomeService
import com.me.repo.entity.home.ArticleData

/**
 * 首页文章的pagingSource，主要配合Paging3进行数据请求与显示
 */
class HomeArticlePagingSource(private var service: HomeService) : PagingSource<Int, ArticleData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleData> {
        return try {
            val pageNum = params.key ?: 1
            val homeData = service.getHomeArticle(pageNum)
            val preKey = if (pageNum > 1) pageNum - 1 else null
            LoadResult.Page(homeData.data?.datas!!, prevKey = preKey, nextKey = pageNum + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleData>): Int? = null

}
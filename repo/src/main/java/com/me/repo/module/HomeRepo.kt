package com.me.repo.module

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.me.repo.api.HomeService
import com.me.repo.base.BaseRepository
import com.me.repo.base.StateLiveData
import com.me.repo.dao.db.AppDatabase
import com.me.repo.entity.home.ArticleData
import com.me.repo.entity.home.BannerData
import com.me.repo.entity.home.DailyQuestionData
import com.me.repo.entity.home.SquareData
import com.me.repo.mediator.ArticleRemoteMediator
import com.me.repo.source.DailyQuestionPagingSource
import com.me.repo.source.SquarePagingDataSource
import kotlinx.coroutines.flow.Flow


class HomeRepo(private val service: HomeService,private val db: AppDatabase) : BaseRepository() {

    private var mArticleType: Int = 0

    companion object {
        private const val PAGE_SIZE = 10
        val config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = 5,
            initialLoadSize = PAGE_SIZE,
            enablePlaceholders = false,
            maxSize = PAGE_SIZE * 3
        )
    }

    /**
     * 请求首页banner
     */
    suspend fun getBanner(bannerLiveData: StateLiveData<List<BannerData>>) {
        execute({ service.getBanner() }, bannerLiveData)
    }


    private val pagingSourceFactory = { db.articleDao().queryLocalArticle(mArticleType) }

    /**
     * 请求首页文章，
     * Room+network进行缓存
     */
    @ExperimentalPagingApi
    fun getHomeArticle(articleType: Int): Flow<PagingData<ArticleData>> {
        mArticleType = articleType
        return Pager(
            config = config,
            remoteMediator = ArticleRemoteMediator(service, db, 1),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    /**
     * 请求每日一问
     */
    fun getDailyQuestion(): Flow<PagingData<DailyQuestionData>> {
        return Pager(config) {
            DailyQuestionPagingSource(service)
        }.flow
    }

    /**
     * 请求广场数据
     */
    fun getSquareData(): Flow<PagingData<SquareData>> {
        return Pager(config) {
            SquarePagingDataSource(service)
        }.flow
    }

}


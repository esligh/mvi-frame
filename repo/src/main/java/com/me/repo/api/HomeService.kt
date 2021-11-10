package com.me.repo.api

import com.me.repo.base.BasePagingResp
import com.me.repo.base.BaseResp
import com.me.repo.entity.home.ArticleData
import com.me.repo.entity.home.BannerData
import com.me.repo.entity.home.DailyQuestionData
import com.me.repo.entity.home.SquareData
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {


    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): BaseResp<BasePagingResp<List<ArticleData>>>


    @GET("banner/json")
    suspend fun getBanner(): BaseResp<List<BannerData>>

    @GET("wenda/list/{page}/json")
    suspend fun getDailyQuestion(@Path("page") page: Int): BaseResp<BasePagingResp<List<DailyQuestionData>>>

    @GET("user_article/list/{page}/json")
    suspend fun getSquareData(@Path("page") page: Int): BaseResp<BasePagingResp<List<SquareData>>>
}
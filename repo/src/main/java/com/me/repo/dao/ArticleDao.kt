package com.me.repo.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.me.repo.entity.home.ArticleData

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleDataList: List<ArticleData>)

    @Query("SELECT * FROM tab_article WHERE articleType =:articleType")
    fun queryLocalArticle(articleType: Int): PagingSource<Int, ArticleData>

    @Query("DELETE FROM tab_article WHERE articleType=:articleType")
    suspend fun clearArticleByType(articleType: Int)

}
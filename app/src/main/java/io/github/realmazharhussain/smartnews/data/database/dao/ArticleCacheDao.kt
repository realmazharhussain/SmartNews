package io.github.realmazharhussain.smartnews.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache

@Dao
interface ArticleCacheDao {
    @Query("SELECT * FROM article_cache WHERE id = :id")
    suspend fun find(id: Int): ArticleCache?

    @Query("SELECT * FROM article_cache")
    fun pagingSource(): PagingSource<Int, ArticleCache>

    @Insert
    suspend fun add(articles: List<ArticleCache>)

    @Query("DELETE FROM article_cache")
    suspend fun removeAll()

    @Query("SELECT COUNT(1) FROM article_cache")
    suspend fun count(): Int
}

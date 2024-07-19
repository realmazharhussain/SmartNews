package io.github.realmazharhussain.smartnews.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache

@Dao
interface ArticleCacheDao {
    @Query("SELECT * FROM article_cache WHERE url = :url")
    suspend fun find(url: String): ArticleCache?

    @Query("SELECT EXISTS (SELECT 1 FROM article_cache WHERE source_id = :sourceId)")
    suspend fun existForSource(sourceId: Long): Boolean

    @Query("SELECT source_id FROM article_cache WHERE url = :url")
    suspend fun getSourceId(url: String): Long?

    @Query("SELECT * FROM article_cache")
    fun pagingSource(): PagingSource<Int, ArticleCache>

    @Upsert
    suspend fun add(vararg article: ArticleCache)

    @Query("DELETE FROM article_cache WHERE url = :url")
    suspend fun remove(url: String)
}

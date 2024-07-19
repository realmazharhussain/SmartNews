package io.github.realmazharhussain.smartnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleSource

@Dao
interface ArticleSourceDao {
    @Query("SELECT * FROM article_sources WHERE id = :sourceId OR name = :name")
    suspend fun find(sourceId: String?, name: String): ArticleSource?

    @Query("SELECT * FROM article_sources WHERE auto_id = :autoId")
    suspend fun find(autoId: Long): ArticleSource?

    @Insert
    suspend fun insert(articleSource: ArticleSource): Long

    @Update
    suspend fun update(articleSource: ArticleSource)

    @Query("DELETE FROM article_sources WHERE auto_id = :autoId")
    suspend fun remove(autoId: Long)
}

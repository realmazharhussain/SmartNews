package io.github.realmazharhussain.smartnews.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.realmazharhussain.smartnews.data.database.entity.SummaryCache

@Dao
interface SummaryCacheDao {
    @Query("SELECT * FROM summary_cache WHERE url = :url")
    suspend fun find(url: String): SummaryCache?

    @Upsert
    suspend fun add(summaryCache: SummaryCache)
}

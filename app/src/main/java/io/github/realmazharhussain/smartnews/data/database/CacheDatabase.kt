package io.github.realmazharhussain.smartnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleCacheDao
import io.github.realmazharhussain.smartnews.data.database.dao.SummaryCacheDao
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache
import io.github.realmazharhussain.smartnews.data.database.entity.SummaryCache

@Database(entities = [ArticleCache::class, SummaryCache::class], version = 5)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun articleCacheDao(): ArticleCacheDao
    abstract fun summaryCacheDao(): SummaryCacheDao
}
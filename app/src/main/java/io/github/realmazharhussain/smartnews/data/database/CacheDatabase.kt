package io.github.realmazharhussain.smartnews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleCacheDao
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleSourceDao
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleSource

@Database(entities = [ArticleSource::class, ArticleCache::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun articleCacheDao(): ArticleCacheDao
    abstract fun articleSourceDao(): ArticleSourceDao
}
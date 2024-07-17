package io.github.realmazharhussain.smartnews.data.database.repository

import io.github.realmazharhussain.smartnews.data.database.dao.ArticleCacheDao
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache
import javax.inject.Inject

class NewsRepositoryLocal @Inject constructor(
    private val articleCacheDao: ArticleCacheDao,
) {
    suspend fun add(articles: List<ArticleCache>) = articleCacheDao.add(articles)
    suspend fun get(id: Int): ArticleCache? = articleCacheDao.find(id)
    suspend fun removeAll() = articleCacheDao.removeAll()
    suspend fun count() = articleCacheDao.count()
    fun pagingSource() = articleCacheDao.pagingSource()
}
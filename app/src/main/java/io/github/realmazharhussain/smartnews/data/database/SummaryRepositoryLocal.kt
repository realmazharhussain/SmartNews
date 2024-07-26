package io.github.realmazharhussain.smartnews.data.database

import io.github.realmazharhussain.smartnews.data.database.dao.SummaryCacheDao
import io.github.realmazharhussain.smartnews.data.database.entity.SummaryCache
import javax.inject.Inject

class SummaryRepositoryLocal @Inject constructor(
    private val summaryCacheDao: SummaryCacheDao,
) {
    suspend fun add(url: String, summary: String) = summaryCacheDao.add(SummaryCache(url, summary))
    suspend fun find(url: String) = summaryCacheDao.find(url)
}

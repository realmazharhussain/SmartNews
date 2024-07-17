package io.github.realmazharhussain.smartnews.data.network.repository

import io.github.realmazharhussain.smartnews.BuildConfig
import io.github.realmazharhussain.smartnews.data.network.service.NewsService
import javax.inject.Inject

class NewsRepositoryRemote @Inject constructor(private val service: NewsService) {
    @Suppress("MemberVisibilityCanBePrivate")
    var apiKey = BuildConfig.NEWS_API_KEY
    @Suppress("MemberVisibilityCanBePrivate")
    var pageSize = 20

    suspend fun everything(query: String, page: Int) = service.everything(query, page, pageSize, apiKey)
}

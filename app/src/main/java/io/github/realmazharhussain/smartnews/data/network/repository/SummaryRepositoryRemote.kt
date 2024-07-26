package io.github.realmazharhussain.smartnews.data.network.repository

import io.github.realmazharhussain.smartnews.BuildConfig
import io.github.realmazharhussain.smartnews.data.network.dto.SummaryReq
import io.github.realmazharhussain.smartnews.data.network.dto.SummaryReq.SourceType
import io.github.realmazharhussain.smartnews.data.network.service.SummaryService
import javax.inject.Inject

class SummaryRepositoryRemote @Inject constructor(private val service: SummaryService) {
    @Suppress("MemberVisibilityCanBePrivate")
    var apiKey = BuildConfig.AI21_API_KEY
    private val authorization get() = "Bearer $apiKey"

    suspend fun summarize(url: String) = service.summarize(authorization, SummaryReq(SourceType.Url, url)).summary
}

package io.github.realmazharhussain.smartnews.data.network.service

import io.github.realmazharhussain.smartnews.data.network.dto.SummaryReq
import io.github.realmazharhussain.smartnews.data.network.dto.SummaryRsp
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SummaryService {
    @POST("summarize")
    suspend fun summarize(
        @Header("Authorization") authorization: String,
        @Body body: SummaryReq
    ): SummaryRsp
}

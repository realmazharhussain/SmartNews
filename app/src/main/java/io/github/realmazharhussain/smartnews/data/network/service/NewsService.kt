package io.github.realmazharhussain.smartnews.data.network.service

import io.github.realmazharhussain.smartnews.data.network.dto.NewsRsp
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun everything(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): NewsRsp
}

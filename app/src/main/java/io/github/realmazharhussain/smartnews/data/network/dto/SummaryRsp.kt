package io.github.realmazharhussain.smartnews.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummaryRsp(
    @Json(name = "id") val id: String,
    @Json(name = "summary") val summary: String
)

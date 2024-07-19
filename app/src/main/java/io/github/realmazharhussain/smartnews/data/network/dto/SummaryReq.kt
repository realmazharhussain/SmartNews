package io.github.realmazharhussain.smartnews.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SummaryReq(
    @Json(name = "sourceType") val sourceType: SourceType = SourceType.Text,
    @Json(name = "source") val source: String
) {
    enum class SourceType {
        @Json(name = "TEXT") Text,
        @Json(name = "URL") Url,
    }
}

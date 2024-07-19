package io.github.realmazharhussain.smartnews.network.dto

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
data class NewsRspJson (
    val status: NewsRspJsonStatus,
    val code: Int = 0,
    val message: String = "",
    val totalResults: Int = 0,
    val articles: List<Article> = emptyList()
)

enum class NewsRspJsonStatus {
    @Json(name = "ok") Ok,
    @Json(name = "error") Error
}

sealed interface NewsRsp {
    data class Ok(
        val totalResults: Int,
        val articles: List<Article>
    ): NewsRsp

    data class Error(
        val code: Int,
        override val message: String
    ): Exception(), NewsRsp
}

@JsonClass(generateAdapter = true)
data class Article (
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
) {
    companion object {
        fun mock() = Article(
            source = Source.mock(),
            author = "Mazhar Hussain",
            title = "Releasing Daikhan 0.1.alpha",
            description = "Announcing the first ever alpha release 0.1.alpha of Daikhan media player",
            url = "https://github.com/flathub/io.gitlab.daikhan.stable",
            urlToImage = null,
            publishedAt = "2024-8-31",
            content = "I have released the first ever alpha version (0.1.alpha) of my media player to the Flathub beta repository"
        )
    }
}

@JsonClass(generateAdapter = true)
data class Source (
    val id: String?,
    val name: String
) {
    companion object {
        fun mock() = Source(id = "io.github.realmazharhussain.blog", name = "Mazhar's Blog")
    }
}

class NewsRspJsonAdapter {
    @FromJson fun newsRspFromJson(newsRspJson: NewsRspJson) = when(newsRspJson.status) {
        NewsRspJsonStatus.Ok -> NewsRsp.Ok(newsRspJson.totalResults, newsRspJson.articles)
        NewsRspJsonStatus.Error -> NewsRsp.Error(newsRspJson.code, newsRspJson.message)
    }

    @ToJson fun newsRspToJson(newsRsp: NewsRsp) = when(newsRsp) {
        is NewsRsp.Ok -> NewsRspJson(status = NewsRspJsonStatus.Ok, totalResults = newsRsp.totalResults, articles = newsRsp.articles)
        is NewsRsp.Error -> NewsRspJson(status = NewsRspJsonStatus.Error, code = newsRsp.code, message = newsRsp.message)
    }
}

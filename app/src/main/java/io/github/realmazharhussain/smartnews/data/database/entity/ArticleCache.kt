package io.github.realmazharhussain.smartnews.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.data.network.dto.Source

@Entity(tableName = "article_cache")
data class ArticleCache(
    @ColumnInfo(name = "source_id", index = true) val sourceId: String?,
    @ColumnInfo(name = "source_name", index = true) val sourceName: String,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {
    fun toArticle() = Article(Source(sourceId, sourceName), author, title, description, url, urlToImage, publishedAt, content, id)

    companion object {
        fun fromArticle(article: Article) = with(article) { ArticleCache(source.id, source.name, author, title, description, url, urlToImage, publishedAt, content, id) }
    }
}

fun Article.toEntity() = ArticleCache.fromArticle(this)

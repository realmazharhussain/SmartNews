package io.github.realmazharhussain.smartnews.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "article_cache", foreignKeys = [ForeignKey(
        ArticleSource::class, childColumns = ["source_id"], parentColumns = ["auto_id"]
    )]
)
data class ArticleCache (
    @ColumnInfo(name = "source_id", index = true) val sourceId: Long,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") @PrimaryKey val url: String,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    @ColumnInfo(name = "content") val content: String
)

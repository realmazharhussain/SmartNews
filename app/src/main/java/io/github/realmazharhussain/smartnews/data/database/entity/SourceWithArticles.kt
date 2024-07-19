package io.github.realmazharhussain.smartnews.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import io.github.realmazharhussain.smartnews.data.network.dto.Article

data class SourceWithArticles(
    @Embedded val source: ArticleSource,
    @Relation(
        parentColumn = "auto_id",
        entityColumn = "source_id"
    ) val articles: List<Article>
)

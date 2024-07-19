package io.github.realmazharhussain.smartnews.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "article_sources",
    indices = [Index("id"), Index("name")]
)
data class ArticleSource(
    @ColumnInfo(name = "auto_id") @PrimaryKey(autoGenerate = true) val autoId: Long = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String
) {
    constructor(id: String?, name: String): this(autoId = 0, id = id.orEmpty(), name = name)
}

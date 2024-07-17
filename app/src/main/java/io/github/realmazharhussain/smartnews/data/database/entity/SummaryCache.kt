package io.github.realmazharhussain.smartnews.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.data.network.dto.Source

@Entity(tableName = "summary_cache")
data class SummaryCache(
    @ColumnInfo(name = "url") @PrimaryKey val url: String,
    @ColumnInfo(name = "summary") val summary: String,
)

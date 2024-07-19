package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
fun NewsItem(article: Article, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                placeholder = rememberVectorPainter(Icons.Default.Downloading),
                error = rememberVectorPainter(Icons.Default.BrokenImage),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(12))
                    .size(60.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.heightIn(60.dp)
            ) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = if (article.author.isNullOrBlank()) article.source.name else "${article.author} - ${article.source.name}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Text(
                    text = article.description ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewsItemPreview() {
    SmartNewsTheme {
        NewsItem(Article.mock(), onClick = {})
    }
}

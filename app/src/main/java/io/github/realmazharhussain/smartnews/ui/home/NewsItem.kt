package io.github.realmazharhussain.smartnews.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.common.navigation.LocalAnimatedVisibilityScope
import io.github.realmazharhussain.smartnews.ui.common.navigation.LocalSharedTransitionScope
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun NewsItem(
    article: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = with(LocalSharedTransitionScope.current) {
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
                    .sharedElement(
                        rememberSharedContentState("image-${article.id}"),
                        LocalAnimatedVisibilityScope.current
                    )
                    .clip(RoundedCornerShape(12))
                    .size(60.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.heightIn(60.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.sharedElement(rememberSharedContentState("title-${article.id}"), LocalAnimatedVisibilityScope.current),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (article.author.isNullOrBlank()) article.source.name else "${article.author} - ${article.source.name}",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.sharedElement(rememberSharedContentState("source-${article.id}"), LocalAnimatedVisibilityScope.current),
                )
            }
        }
    }
}

@Preview
@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun NewsItemPreview() {
    SmartNewsTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalAnimatedVisibilityScope provides this@AnimatedVisibility
                ) {
                    NewsItem(Article.mock(), onClick = {})
                }
            }
        }
    }
}

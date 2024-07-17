package io.github.realmazharhussain.smartnews.ui.common

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme

@Composable
fun NewsItem(modifier: Modifier = Modifier) {
    Card(
        onClick = {},
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = null,
                Modifier
                    .clip(RoundedCornerShape(12))
                    .size(60.dp)
            )
            Spacer(Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.heightIn(60.dp)
            ) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(text = "News Article Title", style = MaterialTheme.typography.titleSmall)
                    Text(
                        text = "Source Company - Writer", style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = "Summary and description of the news article",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPreview() {
    SmartNewsTheme {
        NewsItem()
    }
}
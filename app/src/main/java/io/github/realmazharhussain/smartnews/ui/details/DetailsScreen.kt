package io.github.realmazharhussain.smartnews.ui.details

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.realmazharhussain.smartnews.R
import io.github.realmazharhussain.smartnews.common.TaskState
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.ui.common.Screen
import io.github.realmazharhussain.smartnews.ui.theme.SmartNewsTheme
import retrofit2.HttpException
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun DetailsScreen(details: TaskState<Article>, summary: TaskState<String>, modifier: Modifier = Modifier) {
    Screen(title = stringResource(R.string.article_details), modifier) {
        if (details !is TaskState.Success) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(
                when(details) {
                    is TaskState.Loading -> stringResource(R.string.loading)
                    is TaskState.Failure -> "Error Occurred: ${details.cause}"
                    else -> "Internal Error"
                }
            ) }
            return@Screen
        }

        val article = details.data
        val context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.downloading_16x9),
                error = rememberVectorPainter(Icons.Default.BrokenImage),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = article.title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "${article.author?.plus(" - ") ?: ""}${article.source.name}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = article.content,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        val intent = CustomTabsIntent.Builder().build()
                        intent.launchUrl(context, Uri.parse(article.url))
                    },
                ) {
                    Text("Read Full Article")
                }

                Spacer(modifier = Modifier.height(4.dp))

                Card(
                    elevation = CardDefaults.elevatedCardElevation(),
                    modifier = Modifier
                        .padding(4.dp)
                        .animateContentSize()
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(
                            text = stringResource(R.string.summary_ai_generated),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        val text = when (summary) {
                            is TaskState.Loading -> stringResource(R.string.generating)
                            is TaskState.Success -> summary.data
                            is TaskState.Failure -> {
                                "Error occurred: " + if (summary.cause is HttpException && summary.cause.code() == 422) {
                                    "Cannot access the article"
                                } else "${summary.cause}"
                            }
                        }

                        for (para in text.split('\n')) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = para,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    SmartNewsTheme {
        DetailsScreen(TaskState.Success(Article.mock()), TaskState.Success(randomText()))
    }
}

private fun randomText(): String {
    var randomTextChars = CharArray(0)
    for (i in 0..Random.nextInt(4..6)) {
        for (j in 0..Random.nextInt(20..30)) {
            val word = CharArray(Random.nextInt(1..8))
            for (k in word.indices) {
                word[k] = Random.nextInt(65..122).toChar()
            }
            randomTextChars += word + ' '
        }
        randomTextChars[randomTextChars.lastIndex] = '\n'
    }
    return randomTextChars.concatToString().removeSuffix("\n")
}

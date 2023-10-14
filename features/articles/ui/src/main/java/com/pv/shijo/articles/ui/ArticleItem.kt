@file:OptIn(ExperimentalMaterial3Api::class)

package com.pv.shijo.articles.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.pv.shijo.entity.Article
import com.pv.shijo.theme.ArticlesTheme

@Composable
fun ArticleCardExpanded(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.semantics {
            onClick()
        },
    ) {
        Column {
            Row {
                ArticleHeaderImage(article.imageUrl)
            }
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        ArticleTitle(
                            article.title,
                            modifier = Modifier.fillMaxWidth((.8f)),
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ArticleMetaData(article.publishedDate, article.type)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    ArticleShortDescription(article.shortDescription)
                    Spacer(modifier = Modifier.height(12.dp))
                    ArticleGame(
                        game = article.game,
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleHeaderImage(
    headerImageUrl: String?,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = headerImageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) {
                imageLoader
            } else {
                painterResource(R.drawable.ic_placeholder_default)
            },
            contentDescription = null, // decorative image,
        )
    }
}

@Composable
fun ArticleTitle(
    articleTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(articleTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}


@Composable
fun ArticleMetaData(
    publishDate: String,
    resourceType: String,
) {
    Text(
        if (resourceType.isNotBlank()) {
            stringResource(R.string.card_meta_data_text, publishDate, resourceType)
        } else {
            publishDate
        },
        style = MaterialTheme.typography.labelSmall,
    )
}

@Composable
fun ArticleShortDescription(
    articleShortDescription: String,
) {
    Text(articleShortDescription, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun ArticleGame(
    game: String,
    modifier: Modifier = Modifier,
) {
    GameTag(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.semantics {
                this.contentDescription = contentDescription
            },
            text = game,
        )
    }
}


@Preview("ArticleCardExpanded")
@Composable
private fun ExpandedArticlePreview(
    @PreviewParameter(ArticlePreviewParameterProvider::class)
    userArticles: List<Article>,
) {
    CompositionLocalProvider(
        LocalInspectionMode provides true,
    ) {
        ArticlesTheme {
            Surface {
                ArticleCardExpanded(
                    modifier = Modifier.wrapContentSize(),
                    article = userArticles[0],
                    onClick = {}
                )
            }
        }
    }
}


@Composable
fun GameTag(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        TextButton(
            onClick = { },
            enabled = true,
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = TagDefaults.DisabledTagContainerAlpha,
                ),
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}


@Preview
@Composable
fun TagPreview() {
    ArticlesTheme {
        GameTag {
            Text("Topic".uppercase())
        }
    }
}

object TagDefaults {
    const val DisabledTagContainerAlpha = 0.12f
}

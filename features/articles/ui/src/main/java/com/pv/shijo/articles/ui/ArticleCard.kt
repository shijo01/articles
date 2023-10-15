@file:OptIn(ExperimentalMaterial3Api::class)

package com.pv.shijo.articles.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pv.shijo.articles.ui.components.ArticleHeaderImage
import com.pv.shijo.articles.ui.components.GameTag
import com.pv.shijo.entity.Article
import com.pv.shijo.theme.ArticlesTheme

@Composable
fun ArticleCard(
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
                ArticleHeaderImage(
                    article.imageUrl
                )
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
                ArticleCard(
                    modifier = Modifier.wrapContentSize(),
                    article = userArticles[0],
                    onClick = {}
                )
            }
        }
    }
}

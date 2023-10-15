package com.pv.shijo.articles.ui

import android.widget.TextView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.material.R
import com.pv.shijo.articles.ArticleViewModel
import com.pv.shijo.articles.ui.components.ArticleHeaderImage
import com.pv.shijo.entity.Article

@Composable
fun ArticleDetailRoute(
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel,
    articleId: String,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick.invoke()
    }

    val articlePagingItems: LazyPagingItems<Article> =
        articleViewModel.articleState.collectAsLazyPagingItems()
    ArticleDetailsScreen(
        articleId = articleId,
        articlePagingItems = articlePagingItems,
        onBackClick = onBackClick
    )
}


@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    articleId: String,
    articlePagingItems: LazyPagingItems<Article>,
    onBackClick: () -> Unit
) {

    val article = articlePagingItems.itemSnapshotList.items.firstOrNull { it.id == articleId }
    Card(
        shape = androidx.compose.ui.graphics.RectangleShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ArticleDetailTopBar(
                onBackClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    ArticleTitle(
                        article?.title.orEmpty(),
                        modifier = Modifier.fillMaxWidth((.8f)),
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ArticleMetaData(article?.author.orEmpty(), article?.publishedDate.orEmpty())
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    ArticleHeaderImage(article?.imageUrl.orEmpty())
                }
                Spacer(modifier = Modifier.height(12.dp))
                ActionLabel(text = "Bookmark") {
                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = com.pv.shijo.articles.ui.R.drawable.baseline_bookmark_border_24),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                        )
                    }
                }
                ActionLabel(text = "Share") {
                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = R.drawable.abc_ic_menu_share_mtrl_alpha),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                        )
                    }
                }
                ActionLabel(text = "3 comments") {
                    IconButton(onClick = { }) {
                        Image(
                            painter = painterResource(id = com.pv.shijo.articles.ui.R.drawable.baseline_announcement_24),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                        )
                    }
                }
                HtmlText(html = article?.content.orEmpty())
            }
        }
    }
}

@Composable
fun ArticleDetailTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = { onBackClick.invoke() }) {
                Image(
                    painter = painterResource(id = R.drawable.abc_ic_ab_back_material),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Article Detail",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
        }

    }

}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.onSurface.toArgb()
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = {
            it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
            it.setTextColor(color)
        }
    )
}

@Composable
fun ActionLabel(
    modifier: Modifier = Modifier,
    text: String,
    icon: @Composable () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        icon()
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
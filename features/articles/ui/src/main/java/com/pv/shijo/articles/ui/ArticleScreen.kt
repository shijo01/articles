package com.pv.shijo.articles.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pv.shijo.articles.ArticleViewModel
import com.pv.shijo.articles.ui.components.ErrorMessage
import com.pv.shijo.articles.ui.components.LoadingNextPageItem
import com.pv.shijo.articles.ui.components.PageLoader
import com.pv.shijo.entity.Article
import com.pv.shijo.theme.ArticlesTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ArticleListRoute(
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel,
    navigateToArticleDetail: (Article) -> Unit
) {
    LaunchedEffect(key1 = true, block = {
        articleViewModel.onFetchPage()
    })
    val articlePagingItems: LazyPagingItems<Article> =
        articleViewModel.articleState.collectAsLazyPagingItems()

    ArticleScreen(
        articlePagingItems = articlePagingItems,
        onArticleClick = {
            navigateToArticleDetail(it)
        })
}

@Composable
internal fun ArticleScreen(
    modifier: Modifier = Modifier,
    articlePagingItems: LazyPagingItems<Article>,
    onArticleClick: (Article) -> Unit
) {
    val state = rememberLazyStaggeredGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 24.dp,
            state = state
        ) {
            articleList(
                articlePagingItems = articlePagingItems,
                onArticleClick = onArticleClick
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridScope.articleList(
    onArticleClick: (Article) -> Unit,
    articlePagingItems: LazyPagingItems<Article>
) {
    items(articlePagingItems.itemCount) { index ->
        articlePagingItems[index]?.let { article: Article ->
            ArticleCard(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .animateItemPlacement(),
                article = article,
            ) {
                onArticleClick(article)
            }
        }
    }

    articlePagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { PageLoader(modifier = Modifier.fillMaxSize()) }
            }

            loadState.refresh is LoadState.Error -> {
                val error = articlePagingItems.loadState.refresh as LoadState.Error
                item {
                    ErrorMessage(
                        modifier = Modifier.fillMaxSize(),
                        message = error.error.localizedMessage!!,
                        onClickRetry = { retry() })
                }
            }

            loadState.append is LoadState.Loading -> {
                item { LoadingNextPageItem(modifier = Modifier) }
            }

            loadState.append is LoadState.Error -> {
                val error = articlePagingItems.loadState.append as LoadState.Error
                item {
                    ErrorMessage(
                        modifier = Modifier,
                        message = error.error.localizedMessage!!,
                        onClickRetry = { retry() })
                }
            }
        }
    }
}


@Preview
@Preview(device = Devices.TABLET)
@Composable
private fun ArticleListPreview(
    @PreviewParameter(ArticlePreviewParameterProvider::class)
    articles: List<Article>,
) {
    ArticlesTheme {
        val articlePagingItems = flowOf(PagingData.from(articles)).collectAsLazyPagingItems()
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(300.dp)) {
            articleList(
                onArticleClick = {},
                articlePagingItems = articlePagingItems
            )
        }
    }
}


@Preview
@Composable
private fun ArticleLoadingPreview(
    @PreviewParameter(ArticlePreviewParameterProvider::class)
    articles: List<Article>,
) {
    val articlePagingItems = flowOf(PagingData.empty<Article>()).collectAsLazyPagingItems()
    ArticlesTheme {
        ArticleScreen(
            onArticleClick = {},
            articlePagingItems = articlePagingItems
        )
    }
}


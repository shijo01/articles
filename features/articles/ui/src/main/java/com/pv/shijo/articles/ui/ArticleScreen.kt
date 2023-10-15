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
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.pv.shijo.articles.ArticleViewModel
import com.pv.shijo.entity.Article
import com.pv.shijo.theme.ArticlesTheme


@Composable
fun ArticleListRoute(
    modifier: Modifier = Modifier,
    articleViewModel: ArticleViewModel,
    navigateToArticleDetail: (Article) -> Unit
) {
    LaunchedEffect(key1 = true, block = {
        articleViewModel.onFetchPage(2)
    })
    val articleListUiState by articleViewModel.articleListUiState.collectAsState()

    ArticleScreen(articleListUiState = articleListUiState, onArticleClick = {
        navigateToArticleDetail(it)
    })
}

@Composable
internal fun ArticleScreen(
    modifier: Modifier = Modifier,
    articleListUiState: ArticleListUiState,
    onArticleClick: (Article) -> Unit
) {
    val state = rememberLazyStaggeredGridState()

    Box(modifier = Modifier.fillMaxSize()) {

        when (articleListUiState) {
            ArticleListUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    Text(text = "Error")
                }
            }

            ArticleListUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is ArticleListUiState.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(300.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 24.dp,
                    state = state
                ) {
                    articleList(
                        articleListUiState = articleListUiState,
                        onArticleClick = onArticleClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyStaggeredGridScope.articleList(
    articleListUiState: ArticleListUiState,
    onArticleClick: (Article) -> Unit
) {
    when (articleListUiState) {
        ArticleListUiState.Error -> Unit
        ArticleListUiState.Loading -> Unit
        is ArticleListUiState.Success -> {
            items(
                items = articleListUiState.articles,
                key = { it.id },
                contentType = { "articleItem" },
            ) { article ->
                ArticleCard(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .animateItemPlacement(),
                    article = article
                ) {
                    onArticleClick(article)
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
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(300.dp)) {
            articleList(
                articleListUiState = ArticleListUiState.Success(articles),
                onArticleClick = {}
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
    ArticlesTheme {
        ArticleScreen(
            articleListUiState = ArticleListUiState.Loading,
            onArticleClick = {}
        )
    }
}

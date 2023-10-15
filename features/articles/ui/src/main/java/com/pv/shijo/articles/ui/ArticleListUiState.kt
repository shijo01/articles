package com.pv.shijo.articles.ui

import com.pv.shijo.entity.Article

sealed interface ArticleListUiState {

    /**
     * The list is still loading.
     */
    object Loading: ArticleListUiState

    /**
     * The article is loaded with the given list of articles.
     */
    data class Success(
        val articles: List<Article>,
    ) : ArticleListUiState

    /**
     * The state is error
     */
    object Error: ArticleListUiState

}
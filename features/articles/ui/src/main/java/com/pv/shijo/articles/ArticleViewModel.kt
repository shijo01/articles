package com.pv.shijo.articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pv.shijo.articles.ui.ArticleListUiState
import com.pv.shijo.articles.usecases.GetArticleUseCase
import com.pv.shijo.entity.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getArticle: GetArticleUseCase
) : ViewModel() {

    private val _articleListUiState =
        MutableStateFlow<ArticleListUiState>(ArticleListUiState.Loading)

    val articleListUiState = _articleListUiState.asStateFlow()

    fun onFetchPage(page: Int) {
        viewModelScope.launch(exceptionHandler) {
            getArticle.invoke(page).map { articleResponse ->
                when (articleResponse) {
                    is DataState.Data -> {
                        _articleListUiState.update {
                            ArticleListUiState.Success(articleResponse.data.articles)
                        }
                    }

                    is DataState.Loading -> {
                        _articleListUiState.update {
                            ArticleListUiState.Loading
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}

val exceptionHandler = CoroutineExceptionHandler { context, error ->
    // Do what you want with the error
    println(error.message)
}
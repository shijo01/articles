package com.pv.shijo.articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pv.shijo.articles.usecases.GetArticleUseCase
import com.pv.shijo.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getArticle: GetArticleUseCase
) : ViewModel() {

    private val _articleState: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(value = PagingData.empty())
    val articleState: MutableStateFlow<PagingData<Article>> get() = _articleState

    suspend fun onFetchPage() {
        viewModelScope.launch(exceptionHandler) {
            getArticle.invoke()
                .distinctUntilChanged()
                .cachedIn(this)
                .collect { pagingData ->
                    _articleState.value = pagingData
                }
        }
    }
}

val exceptionHandler = CoroutineExceptionHandler { context, error ->
    println(error.message)
}
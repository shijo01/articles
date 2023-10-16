package com.pv.shijo.articles

import androidx.paging.PagingData
import app.cash.turbine.test
import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.articles.ui.PreviewParameterData.articles
import com.pv.shijo.articles.usecases.GetArticleUseCase
import com.pv.shijo.core.network.utils.DispatcherProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {
    private lateinit var dispatcherProvider: DispatcherProvider
    private lateinit var getArticlesUseCase: GetArticleUseCase

    @Mock
    private lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        getArticlesUseCase = GetArticleUseCase(articleRepository)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        runTest {
            val articlePagingItems = flowOf(PagingData.from(articles))
            doReturn(articlePagingItems).`when`(articleRepository).getArticles()
            val viewModel = ArticleViewModel(dispatcherProvider, getArticlesUseCase)

            viewModel.articleState.test {
                assertEquals(PagingData.from(articles), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            viewModel.onFetchPage()
        }
    }


}
package com.pv.shijo.articles.usecases

import androidx.paging.PagingData
import com.pv.shijo.articles.repositories.ArticleRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetArticleUseCaseTest {
    private lateinit var getArticleUseCase: GetArticleUseCase

    @Mock
    private lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp() {
        getArticleUseCase = GetArticleUseCase(articleRepository)
    }

    @Test
    fun `test use case return flow `() = runTest{
        val articlePagingItems = flowOf(PagingData.from(emptyList()))
        Mockito.doReturn(articlePagingItems).`when`(articleRepository).getArticles()

        val result = getArticleUseCase.invoke()

        assertEquals(1, result.toList().size)
    }
}
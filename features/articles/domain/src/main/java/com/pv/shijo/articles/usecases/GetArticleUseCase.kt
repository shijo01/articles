package com.pv.shijo.articles.usecases

import androidx.paging.PagingData
import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.entity.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(page: Int = 1): Flow<PagingData<Article>> {
        return articleRepository.getArticles(page)
    }
}
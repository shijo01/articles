package com.pv.shijo.articles.usecases

import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.entity.ArticleResponse
import com.pv.shijo.entity.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke(page: Int = 0): Flow<DataState<ArticleResponse>> = flow {
        emit(DataState.Loading())
        emit(DataState.Data(articleRepository.getArticles(page)))
    }
}
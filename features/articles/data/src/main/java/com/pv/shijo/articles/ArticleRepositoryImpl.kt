package com.pv.shijo.articles

import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.entity.ArticleResponse
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val articleNetworkDataSource: ArticleNetworkDataSource) :
    ArticleRepository {
    override suspend fun getArticles(page: Int): ArticleResponse =
        articleNetworkDataSource.getArticles(page).toEntity()
}
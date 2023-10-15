package com.pv.shijo.articles

import com.pv.shijo.core.network.ArticleApi
import com.pv.shijo.core.network.dto.ArticleResponse
import javax.inject.Inject

class RetrofitArticleNetwork @Inject constructor(private val articleApi: ArticleApi) :
    ArticleNetworkDataSource {
    override suspend fun getArticles(page: Int): ArticleResponse {
        return articleApi.getArticles(page)
    }
}
package com.pv.shijo.articles

import com.pv.shijo.core.network.dto.ArticleResponse

interface ArticleNetworkDataSource {
    suspend fun getArticles(page: Int): ArticleResponse
}

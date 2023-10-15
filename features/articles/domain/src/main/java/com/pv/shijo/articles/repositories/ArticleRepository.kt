package com.pv.shijo.articles.repositories

import com.pv.shijo.entity.Article
import com.pv.shijo.entity.ArticleResponse
import com.pv.shijo.entity.Response

interface ArticleRepository {
    suspend fun getArticles(page: Int): ArticleResponse
}
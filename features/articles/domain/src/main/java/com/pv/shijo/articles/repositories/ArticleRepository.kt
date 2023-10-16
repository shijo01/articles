package com.pv.shijo.articles.repositories

import androidx.paging.PagingData
import com.pv.shijo.entity.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(): Flow<PagingData<Article>>
}
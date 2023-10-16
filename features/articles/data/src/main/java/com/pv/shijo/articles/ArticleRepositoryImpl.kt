package com.pv.shijo.articles

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.pv.shijo.articles.mapper.toEntity
import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.core.network.dto.ArticleDto
import com.pv.shijo.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleNetworkDataSource: ArticleNetworkDataSource,
    private val articlePagingSource: ArticlePagingSource
) :
    ArticleRepository {
    override suspend fun getArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                articlePagingSource
            }
        )
            .flow
            .map { value: PagingData<ArticleDto> ->
                value.map { it.toEntity() }
            }
    }
}
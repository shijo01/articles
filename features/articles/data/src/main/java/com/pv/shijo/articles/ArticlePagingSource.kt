package com.pv.shijo.articles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val dataSource: ArticleNetworkDataSource,
) : PagingSource<Int, com.pv.shijo.core.network.dto.ArticleDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.pv.shijo.core.network.dto.ArticleDto> {
        return try {
            val currentPage = params.key ?: 1
            val articleResponse = dataSource.getArticles(page = currentPage)
            LoadResult.Page(
                data = articleResponse.articles ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (articleResponse.hasNextPage == true) currentPage + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.pv.shijo.core.network.dto.ArticleDto>): Int? {
        return state.anchorPosition
    }

}
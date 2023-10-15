package com.pv.shijo.articles

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pv.shijo.articles.mapper.toEntity
import com.pv.shijo.entity.Article
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArticlePagingSource @Inject constructor(
    private val dataSource: ArticleNetworkDataSource,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: 1
            val articleResponse = dataSource.getArticles(page = currentPage)
            LoadResult.Page(
                data = articleResponse.toEntity().articles,
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

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

}
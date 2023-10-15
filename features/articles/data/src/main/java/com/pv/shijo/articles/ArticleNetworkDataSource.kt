package com.pv.shijo.articles

import android.annotation.SuppressLint
import com.pv.shijo.core.network.dto.ArticleResponse
import com.pv.shijo.entity.Article
import java.text.SimpleDateFormat
import java.util.Date

interface ArticleNetworkDataSource {
    suspend fun getArticles(page: Int): ArticleResponse
}

fun ArticleResponse.toEntity(): com.pv.shijo.entity.ArticleResponse {
    return com.pv.shijo.entity.ArticleResponse(
        hasNextPage = this.hasNextPage ?: false,
        articles = this.articles?.map { article ->
            Article(
                id = article.id.orEmpty(),
                title = article.title?.english.orEmpty(),
                author = article.author?.username.orEmpty(),
                publishedDate = article.createdDate.toFormattedDate(),
                shortDescription = article.shortDescription?.english.orEmpty(),
                content = article.content?.english.orEmpty(),
                imageUrl = article.image.orEmpty(),
                type = article.category?.name.orEmpty(),
                game = article.game.orEmpty()
            )
        } ?: emptyList()
    )
}

@SuppressLint("SimpleDateFormat")
fun Date?.toFormattedDate(): String {
    if (this == null)
        return ""

    val simpleDateFormat = SimpleDateFormat("E dd MMM yyyy")
    return simpleDateFormat.format(this)
}
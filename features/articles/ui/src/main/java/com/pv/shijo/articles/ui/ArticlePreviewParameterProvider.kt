package com.pv.shijo.articles.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pv.shijo.articles.ui.PreviewParameterData.articles
import com.pv.shijo.entity.Article

class ArticlePreviewParameterProvider : PreviewParameterProvider<List<Article>> {

    override val values: Sequence<List<Article>> = sequenceOf(articles)
}

object PreviewParameterData {
    val articles = listOf<Article>(
        Article(
            id = "propriae",
            title = "dictumst",
            author = "nullam",
            publishedDate = "definiebas",
            shortDescription = "litora",
            content = "viris",
            imageUrl = "https://search.yahoo.com/search?p=sanctus",
            type = "meliore",
            game = "utroque"
        )
    )

}
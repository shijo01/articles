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
        ),
        Article(
            id = "aliquam",
            title = "efficiantur",
            author = "suas",
            publishedDate = "nostrum",
            shortDescription = "no",
            content = "vehicula",
            imageUrl = "https://www.google.com/#q=quod",
            type = "veniam",
            game = "persecuti"
        ),
        Article(
            id = "aenean",
            title = "saepe",
            author = "quod",
            publishedDate = "simul",
            shortDescription = "vestibulum",
            content = "quas",
            imageUrl = "https://search.yahoo.com/search?p=graeco",
            type = "homero",
            game = "aliquam"
        ),
        Article(
            id = "nascetur",
            title = "suspendisse",
            author = "platonem",
            publishedDate = "dissentiunt",
            shortDescription = "sadipscing",
            content = "finibus",
            imageUrl = "https://www.google.com/#q=habitasse",
            type = "similique",
            game = "luptatum"
        )
    )

}
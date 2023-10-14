package com.pv.shijo.core.network.dto


import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean?
)
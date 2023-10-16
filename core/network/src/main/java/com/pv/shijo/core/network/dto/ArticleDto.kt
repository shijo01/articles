package com.pv.shijo.core.network.dto


import com.google.gson.annotations.SerializedName
import java.util.Date

data class ArticleDto(
    @SerializedName("articleStatus")
    val articleStatus: String?,
    @SerializedName("author")
    val author: Author?,
    @SerializedName("category")
    val category: Category?,
    @SerializedName("content")
    val content: Content?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("createdDate")
    val createdDate: Date?,
    @SerializedName("createdOn")
    val createdOn: String?,
    @SerializedName("fullText")
    val fullText: String?,
    @SerializedName("game")
    val game: String?,
    @SerializedName("gameDetails")
    val gameDetails: String?,
    @SerializedName("genre")
    val genre: List<String?>?,
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("isFeature")
    val isFeature: Boolean?,
    @SerializedName("minRead")
    val minRead: Int?,
    @SerializedName("platform")
    val platform: List<String?>?,
    @SerializedName("shortDescription")
    val shortDescription: ShortDescription?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("title")
    val title: Title?,
    @SerializedName("updatedBy")
    val updatedBy: String?,
    @SerializedName("updatedOn")
    val updatedOn: String?,
    @SerializedName("views")
    val views: Int?
)
package com.pv.shijo.entity

data class Article(
    val id: String,
    val title: String,
    val author: String,
    val publishedDate: String,
    val shortDescription: String,
    val content: String,
    val imageUrl: String,
    val type: String,
    val game: String
)
package com.pv.shijo.core.network

import com.pv.shijo.core.network.dto.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleApi {
    @GET("articles/{page}")
    suspend fun getArticles(@Path("page") page: Int): ArticleResponse
}
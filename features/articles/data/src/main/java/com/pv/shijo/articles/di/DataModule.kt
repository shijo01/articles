package com.pv.shijo.articles.di

import com.pv.shijo.articles.ArticleNetworkDataSource
import com.pv.shijo.articles.ArticleRepositoryImpl
import com.pv.shijo.articles.RetrofitArticleNetwork
import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.core.network.ArticleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideArticleNetworkDataSource(articleApi: ArticleApi): ArticleNetworkDataSource =
        RetrofitArticleNetwork(articleApi = articleApi)

    @Provides
    fun provideArticleRepository(articleNetworkDataSource: ArticleNetworkDataSource): ArticleRepository =
        ArticleRepositoryImpl(articleNetworkDataSource)
}
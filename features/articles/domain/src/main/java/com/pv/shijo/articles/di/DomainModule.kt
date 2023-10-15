package com.pv.shijo.articles.di

import com.pv.shijo.articles.repositories.ArticleRepository
import com.pv.shijo.articles.usecases.GetArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideGetArticleUseCase(
        articleRepository: ArticleRepository
    ): GetArticleUseCase = GetArticleUseCase(articleRepository)
}
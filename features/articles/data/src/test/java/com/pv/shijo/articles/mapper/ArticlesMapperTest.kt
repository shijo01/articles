package com.pv.shijo.articles.mapper

import com.pv.shijo.core.network.dto.ArticleDto
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ArticlesMapperTest {

    @Test
    fun `ArticleDto toEntity() should return ArticleEntity`() {
        val articleDto = ArticleDto(
            articleStatus = null,
            author = null,
            category = null,
            content = null,
            createdBy = null,
            createdDate = null,
            createdOn = null,
            fullText = "test data",
            game = "test game",
            gameDetails = null,
            genre = listOf(),
            _id = null,
            id = "123",
            image = null,
            isFeature = null,
            minRead = null,
            platform = listOf(),
            shortDescription = null,
            slug = null,
            status = null,
            tags = listOf(),
            title = null,
            updatedBy = null,
            updatedOn = null,
            views = null
        )

        val article = articleDto.toEntity()

        assertEquals("123", article.id)
    }
}
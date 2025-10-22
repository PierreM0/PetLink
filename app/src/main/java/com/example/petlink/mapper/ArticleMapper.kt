package com.example.petlink.mapper

import com.example.petlink.model.Article
import com.example.petlink.model.SingleArticleDto
import java.time.LocalDate

class ArticleMapper {
    fun mapArticleDtoToArticle(movieDto: SingleArticleDto): Article {
        return with(movieDto) {
            Article(
                id = id,
                title = title,
                content = content,
                date = LocalDate.parse(creationDate),
                imageUrl = imageUrl
            )
        }
    }
}
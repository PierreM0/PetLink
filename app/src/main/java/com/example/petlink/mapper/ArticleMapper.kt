package com.example.petlink.mapper

import com.example.petlink.model.Article
import com.example.petlink.model.SingleArticleDto
import java.time.LocalDate

class ArticleMapper {
    fun mapArticleDtoToArticle(articleDto: SingleArticleDto): Article {
        return with(articleDto) {
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
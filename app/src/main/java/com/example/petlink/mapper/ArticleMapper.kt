package com.example.petlink.mapper

import com.example.petlink.model.Article
import com.example.petlink.model.dto.ArticleDTO
import java.time.LocalDate

class ArticleMapper {
    fun mapArticleDtoToArticle(articleDto: ArticleDTO): Article {
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
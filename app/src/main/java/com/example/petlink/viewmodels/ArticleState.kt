package com.example.petlink.viewmodels

import com.example.petlink.model.Article

data class ArticleState(
    val articles: List<Article> = listOf(),
    val selectedArticle: Article? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
)

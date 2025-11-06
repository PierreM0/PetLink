package com.example.petlink.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDTO (
    val id: Int,
    val title: String,
    val content: String,
    val imageUrl: String,
    val creationDate: String
)
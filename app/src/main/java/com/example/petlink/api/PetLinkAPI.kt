package com.example.petlink.api

import com.example.petlink.mapper.AnimalMapper
import com.example.petlink.mapper.ArticleMapper
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalDTO
import com.example.petlink.model.Article
import com.example.petlink.model.ArticleDTO
import com.example.petlink.network.KtorClient
import io.ktor.http.HttpMethod

object PetLinkAPI {
    private lateinit var animals: List<Animal>
    private const val ANIMAL_ENDPOINT = "animals"
    private val animalMapper = AnimalMapper()

    private lateinit var articles: List<Article>
    private const val ARTICLE_ENDPOINT = "articles"
    private val articleMapper = ArticleMapper()

    suspend fun getAnimals(): List<Animal> {
        if (!this::animals.isInitialized) {
            val animalsData: List<AnimalDTO> = KtorClient.httpCall(HttpMethod.Get, ANIMAL_ENDPOINT)
            animals = animalsData.map { animalMapper.mapAnimalDtoToAnimal(it) }
        }

        return animals
    }

    suspend fun getArticles(): List<Article> {
        if (!this::articles.isInitialized) {
            val articlesData: List<ArticleDTO> = KtorClient.httpCall(HttpMethod.Get, ARTICLE_ENDPOINT)
            articles = articlesData.map { articleMapper.mapArticleDtoToArticle(it) }
        }

        return articles
    }
}
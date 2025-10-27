package com.example.petlink.api

import com.example.petlink.mapper.AnimalMapper
import com.example.petlink.mapper.ArticleMapper
import com.example.petlink.mapper.VeterinaryMapper
import com.example.petlink.model.AdoptionAnimal
import com.example.petlink.model.AdoptionAnimalDTO
import com.example.petlink.model.Article
import com.example.petlink.model.ArticleDTO
import com.example.petlink.model.Veterinary
import com.example.petlink.model.VeterinaryDTO
import com.example.petlink.network.KtorClient
import io.ktor.http.HttpMethod

object PetLinkAPI {
    private lateinit var adoptionAnimals: List<AdoptionAnimal>
    private const val ANIMAL_ENDPOINT = "animals"
    private val animalMapper = AnimalMapper()

    private lateinit var articles: List<Article>
    private const val ARTICLE_ENDPOINT = "articles"
    private val articleMapper = ArticleMapper()

    private lateinit var veterinaries : List<Veterinary>
    private const val VETERINARY_ENDPOINT = "veterinaries"
    private val veterinaryMapper = VeterinaryMapper()

    suspend fun getAnimals(): List<AdoptionAnimal> {
        if (!this::adoptionAnimals.isInitialized) {
            val animalsData: List<AdoptionAnimalDTO> = KtorClient.httpCall(HttpMethod.Get, ANIMAL_ENDPOINT)
            adoptionAnimals = animalsData.map { animalMapper.mapAnimalDtoToAnimal(it) }
        }

        return adoptionAnimals
    }

    suspend fun getArticles(): List<Article> {
        if (!this::articles.isInitialized) {
            val articlesData: List<ArticleDTO> = KtorClient.httpCall(HttpMethod.Get, ARTICLE_ENDPOINT)
            articles = articlesData.map { articleMapper.mapArticleDtoToArticle(it) }
        }

        return articles
    }

    suspend fun getVeterinaries(): List<Veterinary> {
        if (!this::veterinaries.isInitialized) {
            val veterinariesData: List<VeterinaryDTO> = KtorClient.httpCall(HttpMethod.Get, VETERINARY_ENDPOINT)
            veterinaries = veterinariesData.map { veterinaryMapper.mapVeterinaryDtoToVeterinary(it)}
        }

        return veterinaries
    }
}
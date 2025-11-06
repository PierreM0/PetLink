package com.example.petlink.api

import com.example.petlink.mapper.AnimalMapper
import com.example.petlink.mapper.ArticleMapper
import com.example.petlink.mapper.CityMapper
import com.example.petlink.mapper.VeterinaryMapper
import com.example.petlink.model.adoption_animal.AdoptionAnimal
import com.example.petlink.model.dto.AdoptionAnimalDTO
import com.example.petlink.model.Article
import com.example.petlink.model.dto.ArticleDTO
import com.example.petlink.model.dto.CityDTO
import com.example.petlink.model.Veterinary
import com.example.petlink.model.dto.VeterinaryDTO
import com.example.petlink.network.KtorClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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

    suspend fun getLatAndLongFrom(cityName : String): Pair<Double, Double>? {
        val client = HttpClient {
            install(ContentNegotiation) { json(
                Json {
                    ignoreUnknownKeys = true
                }
            ) }
        }

        return try {
            val response: List<CityDTO> = client.get("https://nominatim.openstreetmap.org/search") {
                url {
                    parameters.append("q", cityName)
                    parameters.append("format", "json")
                    parameters.append("limit", "1")
                }
                headers.append("User-Agent", "PetLink/1.0")
            }.body()

            if (response.isNotEmpty()) {
                val cityMapper = CityMapper()
                val city = cityMapper.mapCityDtoToCity(response.first())
                Pair(city.latitude, city.longitude)
            } else null
        } catch (e: Exception) {
            println("Ktor Error : ${e.message}")
            null
        } finally {
            client.close()
        }
    }
}
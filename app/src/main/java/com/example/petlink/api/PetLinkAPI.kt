package com.example.petlink.api

import com.example.petlink.mapper.AnimalMapper
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalDTO
import com.example.petlink.network.KtorClient
import io.ktor.http.HttpMethod

object PetLinkAPI {
    private lateinit var animals: List<Animal>
    private const val ANIMAL_ENDPOINT = "animals"
    private val animalMapper = AnimalMapper()

    suspend fun getAnimals(): List<Animal> {
        if (!this::animals.isInitialized) {
            val animalsData: List<AnimalDTO> = KtorClient.httpCall(HttpMethod.Get, ANIMAL_ENDPOINT)
            animals = animalsData.map { animalMapper.mapAnimalDtoToAnimal(it) }
        }

        return animals
    }
}
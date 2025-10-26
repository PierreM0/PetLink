package com.example.petlink.viewmodels

import com.example.petlink.model.AdoptionAnimal
import com.example.petlink.model.AdoptionAnimalFilters

data class AnimalState (
    val adoptionAnimals: List<AdoptionAnimal> = listOf(),
    val selectedAdoptionAnimal: AdoptionAnimal? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val filters: AdoptionAnimalFilters = AdoptionAnimalFilters()
)
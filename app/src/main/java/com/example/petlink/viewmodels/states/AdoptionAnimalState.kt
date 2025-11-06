package com.example.petlink.viewmodels.states

import com.example.petlink.model.adoption_animal.AdoptionAnimal
import com.example.petlink.model.adoption_animal.AdoptionAnimalFilters

data class AdoptionAnimalState (
    val adoptionAnimals: List<AdoptionAnimal> = listOf(),
    val selectedAdoptionAnimal: AdoptionAnimal? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val filters: AdoptionAnimalFilters = AdoptionAnimalFilters()
)
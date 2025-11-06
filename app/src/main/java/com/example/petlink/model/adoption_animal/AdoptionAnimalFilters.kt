package com.example.petlink.model.adoption_animal

data class AdoptionAnimalFilters(
    val location: String = "",
    val species: AdoptionAnimalSpecies = AdoptionAnimalSpecies.All,
    val ageRange: AdoptionAnimalAgeRange = AdoptionAnimalAgeRange.All
)
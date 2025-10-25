package com.example.petlink.model

data class AnimalFilters(
    val location: String = "",
    val species: AnimalSpecies = AnimalSpecies.All,
    val ageRange: AnimalAgeRange = AnimalAgeRange.All
)
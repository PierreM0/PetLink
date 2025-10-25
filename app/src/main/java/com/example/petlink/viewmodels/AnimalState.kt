package com.example.petlink.viewmodels

import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalFilters

data class AnimalState (
    val animals: List<Animal> = listOf(),
    val selectedAnimal: Animal? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null,
    val filters: AnimalFilters = AnimalFilters()
)
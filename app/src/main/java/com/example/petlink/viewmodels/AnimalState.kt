package com.example.petlink.viewmodels

import com.example.petlink.model.Animal

data class AnimalState (
    val animals: List<Animal> = listOf(),
    val selectedAnimal: Animal? = null,
    val isLoading: Boolean = false,
    val error: Exception? = null
)
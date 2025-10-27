package com.example.petlink.viewmodels

import com.example.petlink.model.Animal

data class HealthRecordState(
    val animals: List<Animal> = listOf(),
    val selectedAnimal: Animal? = null
)
package com.example.petlink.viewmodels

import com.example.petlink.model.Animal

data class HealthRecordState(
    val animals: MutableList<Animal> = mutableListOf(),
    val selectedAnimal: Animal? = null
)
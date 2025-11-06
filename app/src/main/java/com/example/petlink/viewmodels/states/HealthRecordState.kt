package com.example.petlink.viewmodels.states

import com.example.petlink.model.animal.Animal

data class HealthRecordState(
    val animals: List<Animal> = listOf(),
    val selectedAnimal: Animal? = null,
    val selectedTabIndex: Int = 0
)
package com.example.petlink.model

import java.time.LocalDate
import java.util.UUID

data class Animal(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val birthDate: LocalDate,
    val pictureUrl: String,
    val events: List<AnimalEvent> = listOf()
)
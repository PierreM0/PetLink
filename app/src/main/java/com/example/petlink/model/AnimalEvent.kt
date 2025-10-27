package com.example.petlink.model

import java.time.LocalDate

enum class AnimalEventType {
    Visit,
    Booster,
    Vaccine
}

data class AnimalEvent(
    val type: AnimalEventType,
    val title: String,
    val localisation: String,
    val date: LocalDate,
    val description: String
)
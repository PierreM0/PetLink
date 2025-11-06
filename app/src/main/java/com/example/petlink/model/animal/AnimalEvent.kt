package com.example.petlink.model.animal

import java.time.LocalDate

enum class AnimalEventType(val displayName: String) {
    Visit("Visite"),
    Booster("Rappel"),
    Vaccine("Vaccin")
}

data class AnimalEvent(
    val type: AnimalEventType,
    val animal: Animal,
    val title: String,
    val localisation: String,
    val date: LocalDate,
    val description: String
)
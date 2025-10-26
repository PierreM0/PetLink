package com.example.petlink.model

import java.time.LocalDate

sealed class AnimalEvent(
    open val title: String,
    open val localisation: String,
    open val date: LocalDate,
    open val description: String
)

data class AnimalVisitEvent(
    override val title: String,
    override val localisation: String,
    override val date: LocalDate,
    override val description: String
) : AnimalEvent(title, localisation, date, description)

data class AnimalBoosterEvent(
    override val title: String,
    override val localisation: String,
    override val date: LocalDate,
    override val description: String
) : AnimalEvent(title, localisation, date, description)

data class AnimalVaccineEvent(
    override val title: String,
    override val localisation: String,
    override val date: LocalDate,
    override val description: String
) : AnimalEvent(title, localisation, date, description)
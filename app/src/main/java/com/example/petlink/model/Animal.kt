package com.example.petlink.model

import java.time.LocalDate
import java.util.UUID

data class Animal(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val birthDate: LocalDate,
    val pictureUrl: String,
    val visits: MutableList<AnimalVisitEvent> = mutableListOf(),
    val boosters: MutableList<AnimalBoosterEvent> = mutableListOf(),
    val vaccines: MutableList<AnimalVaccineEvent> = mutableListOf()
)
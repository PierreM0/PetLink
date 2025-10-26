package com.example.petlink.model

import java.time.LocalDate

data class Animal(
    val name: String,
    val birthDate: LocalDate,
    val pictureUrl: String,
    val visits: MutableList<AnimalVisitEvent> = mutableListOf(),
    val boosters: MutableList<AnimalBoosterEvent> = mutableListOf(),
    val vaccines: MutableList<AnimalVaccineEvent> = mutableListOf()
)
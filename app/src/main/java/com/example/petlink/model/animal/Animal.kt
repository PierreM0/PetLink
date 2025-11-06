package com.example.petlink.model.animal

import android.net.Uri
import java.time.LocalDate
import java.util.UUID

data class Animal(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val species: AnimalSpecies,
    val birthDate: LocalDate?,
    val pictureUri: Uri,
    val events: MutableList<AnimalEvent> = mutableListOf()
)
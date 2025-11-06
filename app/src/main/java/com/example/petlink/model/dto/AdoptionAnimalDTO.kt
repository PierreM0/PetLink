package com.example.petlink.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdoptionAnimalDTO (
    val id: Int,
    val imageUrl: String,
    val description: String,
    val name: String,
    val location: String,
    val refuge: String,
    val species: String,
    val birthdate: String,
    val refugeNumber: String,
    val publicationDate: String
)
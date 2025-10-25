package com.example.petlink.model

import kotlinx.serialization.Serializable

@Serializable
data class AnimalDTO (
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
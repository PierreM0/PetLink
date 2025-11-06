package com.example.petlink.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class VeterinaryDTO(
    val id: Int,
    val name: String,
    val openingHour: Int,
    val closingHour: Int,
    val phoneNumber: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String
)
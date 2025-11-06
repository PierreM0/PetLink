package com.example.petlink.model

import kotlinx.serialization.Serializable

@Serializable
data class CityDTO (
    val name : String,
    val lat : String,
    val lon : String
)
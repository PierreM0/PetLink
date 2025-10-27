package com.example.petlink.model

import android.net.Uri

data class Veterinary(
    val id: Int,
    val name: String,
    val openingHour: Int,
    val closingHour: Int,
    val phoneNumber: Uri,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String
)

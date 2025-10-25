package com.example.petlink.model

import android.net.Uri
import java.time.LocalDate

data class Animal (
    val id: Int,
    val imageUrl: String,
    val description: String,
    val name: String,
    val location: String,
    val refuge: String,
    val species: String,
    val age: Int,
    val refugeNumber: Uri,
    val publicationDate: LocalDate
)
package com.example.petlink.model.adoption_animal

import android.net.Uri
import java.time.LocalDate

data class AdoptionAnimal (
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
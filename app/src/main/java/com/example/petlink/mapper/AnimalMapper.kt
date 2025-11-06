package com.example.petlink.mapper

import androidx.core.net.toUri
import com.example.petlink.model.adoption_animal.AdoptionAnimal
import com.example.petlink.model.dto.AdoptionAnimalDTO
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class AnimalMapper {
    fun mapAnimalDtoToAnimal(adoptionAnimalDto: AdoptionAnimalDTO): AdoptionAnimal {
        with(adoptionAnimalDto) {
            val birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ISO_DATE)
            val today = LocalDate.now()
            val age = Period.between(birthDate, today).years

            return AdoptionAnimal(
                id = id,
                imageUrl = imageUrl,
                description = description,
                name = name,
                location = location,
                refuge = refuge,
                species = species,
                age = age,
                refugeNumber = "tel:${refugeNumber}".toUri(),
                publicationDate = LocalDate.parse(publicationDate, DateTimeFormatter.ISO_DATE)
            )
        }
    }
}
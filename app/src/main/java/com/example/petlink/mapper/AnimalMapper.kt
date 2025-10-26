package com.example.petlink.mapper

import androidx.core.net.toUri
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalDTO
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class AnimalMapper {
    fun mapAnimalDtoToAnimal(animalDto: AnimalDTO): Animal {
        with(animalDto) {
            val birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ISO_DATE)
            val today = LocalDate.now()
            val age = Period.between(birthDate, today).years

            return Animal(
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
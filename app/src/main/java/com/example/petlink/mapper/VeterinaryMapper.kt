package com.example.petlink.mapper

import androidx.core.net.toUri
import com.example.petlink.model.Veterinary
import com.example.petlink.model.VeterinaryDTO

class VeterinaryMapper {
    fun mapVeterinaryDtoToVeterinary(veterinaryDto: VeterinaryDTO): Veterinary {
        return with(veterinaryDto) {
            Veterinary(
                id = id,
                name = name,
                openingHour = openingHour,
                closingHour = closingHour,
                phoneNumber = "tel:${phoneNumber}".toUri(),
                city = city,
                latitude = latitude,
                longitude = longitude,
                imageUrl = imageUrl
            )
        }
    }
}
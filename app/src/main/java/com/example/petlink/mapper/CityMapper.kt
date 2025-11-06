package com.example.petlink.mapper

import com.example.petlink.model.City
import com.example.petlink.model.CityDTO

class CityMapper {
    fun mapCityDtoToCity(cityDto: CityDTO): City {
        return with(cityDto) {
            City(
                name = cityDto.name,
                latitude = cityDto.lat.toDouble(),
                longitude = cityDto.lon.toDouble()
            )
        }
    }
}
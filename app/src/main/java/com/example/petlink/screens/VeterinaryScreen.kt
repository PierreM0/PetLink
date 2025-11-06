package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.petlink.components.SearchBar
import com.example.petlink.components.VeterinaryCard
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.VeterinaryViewModel

@Composable
fun VeterinaryScreen(veterinaryViewModel: VeterinaryViewModel) {
    val veterinaryState = veterinaryViewModel.stateFlow.collectAsState().value

    var citySearchValue by remember { mutableStateOf(TextFieldValue("") )}
    var searchCityLatLong by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    LaunchedEffect(citySearchValue) {
        searchCityLatLong = veterinaryViewModel.getLatAndLongFrom(citySearchValue.text)
        println("La ville ${citySearchValue.text} à pour lat : ${searchCityLatLong?.first} et pour long : ${searchCityLatLong?.second}")
    }

    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SearchBar(
            value = citySearchValue,
            onValueChange = { value -> citySearchValue = value},
            placeholderText = "Rechercher par ville",
            isLocationSearch = false
        )
        if (veterinaryState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val veterinaries = veterinaryState.veterinaries
            val filteredList = searchCityLatLong?.let { city ->
                veterinaries.sortedBy { veterinary ->
                    veterinaryViewModel.distanceInKm(
                        lat1 = city.first,
                        lon1 = city.second,
                        lat2 = veterinary.latitude,
                        lon2 = veterinary.longitude
                    )
                }
            } ?: veterinaries
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredList) { veterinary ->
                    VeterinaryCard(veterinary)
                }
            }
        }
    }
}
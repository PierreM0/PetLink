package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.inputs.SearchBar
import com.example.petlink.components.cards.VeterinaryCard
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.VeterinaryViewModel
import kotlinx.coroutines.launch

@Composable
fun VeterinaryScreen(veterinaryViewModel: VeterinaryViewModel) {
    val veterinaryState = veterinaryViewModel.stateFlow.collectAsState().value

    var citySearchValue by remember { mutableStateOf(TextFieldValue("") )}
    var searchCityLatLong by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var lastSearchedCity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(
                value = citySearchValue,
                onValueChange = { value -> citySearchValue = value },
                placeholderText = "Rechercher par ville",
                isLocationSearch = true,
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
            val scope = rememberCoroutineScope()
            Button(
                onClick = {
                    scope.launch {
                        isButtonEnabled = false
                        searchCityLatLong = veterinaryViewModel.getLatAndLongFrom(citySearchValue.text)
                        if(searchCityLatLong?.first != null)
                            lastSearchedCity = citySearchValue.text
                        isButtonEnabled = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainGreen,
                    contentColor = White
                ),
                enabled = isButtonEnabled
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = Icons.AutoMirrored.Outlined.Send.toString(),
                )
            }
        }
        searchCityLatLong?.let { city ->
            Text(
                text = "Résultats proches de \"${lastSearchedCity}\"",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }
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
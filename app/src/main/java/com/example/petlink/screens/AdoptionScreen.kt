package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.AnimalCard
import com.example.petlink.components.Dropdown
import com.example.petlink.components.RawButton
import com.example.petlink.components.SearchBar
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalAgeRange
import com.example.petlink.model.AnimalSpecies
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.AnimalState
import com.example.petlink.viewmodels.AnimalViewModel

// TODO filtres dans le viewmodel

@Composable
fun AdoptionScreen(animalViewModel: AnimalViewModel, onDetails: (Animal) -> Unit) {
    val animalState = animalViewModel.stateFlow.collectAsState().value

    val animals = animalState.animals

    var locationSearchValue by remember { mutableStateOf(TextFieldValue("")) }
    var speciesValue by remember { mutableStateOf(animalState.filters.species.displayName) }
    var ageRangeValue by remember { mutableStateOf(animalState.filters.ageRange.displayName)}

    // Contenu principal
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundGreen)
            .padding(24.dp),
    ) {
        // Section filtres
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Barre de recherche par localisation
            SearchBar(value = locationSearchValue,
                onValueChange = {
                    value -> locationSearchValue = value
                    animalViewModel.setLocationFilter(value.text)
                                },
                placeholderText = "Rechercher par localisation",
                isLocationSearch = true)

            // Filtres espèces & âges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filtre espèces
                Dropdown(value = speciesValue,
                    onValueChange = {
                        value -> speciesValue = value
                        animalViewModel.setSpeciesFilter(value)
                                    },
                    items = AnimalSpecies.getAllDisplayNames(),
                    modifier = Modifier.weight(1f))

                // Filtre âges
                Dropdown(value = ageRangeValue,
                    onValueChange = {
                        value -> ageRangeValue = value
                        animalViewModel.setAgeRangeFilter(value)
                                    },
                    items = AnimalAgeRange.getAllDisplayNames(),
                    modifier = Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(32.dp))

        // Contenu principal (loading ou animals)
        if (animalState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else {
            val filteredAnimals = animalViewModel.getFilteredAnimals()

            Text(text = "${filteredAnimals.size} résultats", fontSize = 24.sp, fontWeight = FontWeight.Medium)

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(filteredAnimals) { animal ->
                    RawButton(onClick = { onDetails(animal) }) {
                        AnimalCard(animal)
                    }
                }
            }
        }
    }
}
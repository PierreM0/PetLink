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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.cards.AdoptionAnimalCard
import com.example.petlink.components.inputs.Dropdown
import com.example.petlink.components.buttons.RawButton
import com.example.petlink.components.inputs.SearchBar
import com.example.petlink.model.adoption_animal.AdoptionAnimal
import com.example.petlink.model.adoption_animal.AdoptionAnimalAgeRange
import com.example.petlink.model.adoption_animal.AdoptionAnimalSpecies
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.AdoptionAnimalViewModel

@Composable
fun AdoptionScreen(adoptionAnimalViewModel: AdoptionAnimalViewModel, onDetails: (AdoptionAnimal) -> Unit) {
    val state = adoptionAnimalViewModel.stateFlow.collectAsState().value

    var locationSearchValue by remember { mutableStateOf(TextFieldValue(state.filters.location)) }
    var selectedSpeciesIndex by remember { mutableIntStateOf(AdoptionAnimalSpecies.entries.indexOf(state.filters.species)) }
    var selectedAgeRangeIndex by remember { mutableIntStateOf(AdoptionAnimalAgeRange.entries.indexOf(state.filters.ageRange)) }

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
                    adoptionAnimalViewModel.setLocationFilter(value.text)
                                },
                placeholderText = "Rechercher par localisation",
                isLocationSearch = true,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
            )

            // Filtres espèces & âges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filtre espèces
                Dropdown(
                    selectedIndex = selectedSpeciesIndex,
                    onSelectedIndex = {
                        selectedSpeciesIndex = it
                        adoptionAnimalViewModel.setSpeciesFilter(AdoptionAnimalSpecies.entries[selectedSpeciesIndex])
                                      },
                    items = AdoptionAnimalSpecies.getAllDisplayNames(),
                    modifier = Modifier.weight(1f)
                )

                // Filtre âges
                Dropdown(
                    selectedIndex = selectedAgeRangeIndex,
                    onSelectedIndex = {
                        selectedAgeRangeIndex = it
                        adoptionAnimalViewModel.setAgeRangeFilter(AdoptionAnimalAgeRange.entries[selectedAgeRangeIndex])
                                      },
                    items = AdoptionAnimalAgeRange.getAllDisplayNames(),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        // Contenu principal (loading ou animals)
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else {
            val filteredAnimals = adoptionAnimalViewModel.getFilteredAnimals()

            Text(text = "${filteredAnimals.size} résultats", fontSize = 24.sp, fontWeight = FontWeight.Medium)

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredAnimals) { animal ->
                    RawButton(onClick = { onDetails(animal) }) {
                        AdoptionAnimalCard(animal)
                    }
                }
            }
        }
    }
}
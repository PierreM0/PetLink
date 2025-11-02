package com.example.petlink.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petlink.components.AnimalCard
import com.example.petlink.components.AnimalEventCard
import com.example.petlink.components.AnimalEventTab
import com.example.petlink.components.AnimalEventTabItem
import com.example.petlink.components.HealthRecordFloatingActionButton
import com.example.petlink.components.RawButton
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.SubGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.HealthRecordState
import com.example.petlink.viewmodels.HealthRecordViewModel

@Composable
fun HealthRecordScreen(
    viewModel: HealthRecordViewModel,
    onAddAnimal: () -> Unit
) {
    val state: HealthRecordState = viewModel.stateFlow.collectAsState().value

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(
                text = "Mes animaux",
                fontSize = 24.sp
            )

            Spacer(Modifier.height(8.dp))

            val animals = state.animals
            if (animals.isEmpty()) {
                Text(
                    text = "Vous n'avez pas ajouté d'animal",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
            else {
                LazyRow(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(animals) { animal ->
                        val isSelected = state.selectedAnimal === animal
                        RawButton(onClick = {
                            viewModel.setSelectedAnimal(animal)
                        }) {
                            AnimalCard(
                                animal = animal,
                                backgroundColor = if (isSelected) SubGreen else White
                            )
                        }
                    }
                }
            }

            state.selectedAnimal?.let { selectedAnimal ->
                Spacer(Modifier.height(32.dp))

                AnimalEventTab(
                    selectedIndex = selectedTabIndex,
                    onTabSelected = { tabItem ->
                        selectedTabIndex = AnimalEventTabItem.entries.indexOf(tabItem)
                    }
                )

                Spacer(Modifier.height(16.dp))

                val selectedTab = AnimalEventTabItem.entries[selectedTabIndex]

                Text(
                    text = "${selectedTab.displayName} de ${selectedAnimal.name}",
                    fontSize = 20.sp
                )

                Spacer(Modifier.height(16.dp))

                val events = selectedAnimal.events.filter { it.type == selectedTab.type }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(events) { event ->
                        AnimalEventCard(event)
                    }
                }
            } ?: run {
                Text(
                    text = "Sélectionnez un animal pour consulter ses rendez-vous",
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        HealthRecordFloatingActionButton(
            onAddAnimal = onAddAnimal,
            onAddEvent = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}
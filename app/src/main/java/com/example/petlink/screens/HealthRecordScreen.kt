package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petlink.components.AnimalCard
import com.example.petlink.model.Animal
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.HealthRecordViewModel
import java.time.LocalDate

@Composable
fun HealthRecordScreen(viewModel: HealthRecordViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Mes animaux",
            fontSize = 24.sp
        )

        Spacer(Modifier.height(8.dp))

        // TODO remove test animal
        val animal = Animal(name = "Finger",
            birthDate = LocalDate.now(),
            pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")

        LazyRow(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AnimalCard(animal)
            }
            item {
                AnimalCard(animal)
            }
            item {
                AnimalCard(animal)
            }
            item {
                AnimalCard(animal)
            }
        }
    }
}
package com.example.petlink.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petlink.model.AnimalEvent
import com.example.petlink.model.AnimalVisitEvent
import com.example.petlink.ui.theme.White
import java.time.LocalDate

@Composable
fun AnimalEventCard(animalEvent: AnimalEvent) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {

    }
}

@Preview
@Composable
fun AnimalEventCardPreview() {
    val animalEvent = AnimalVisitEvent(title = "Consultation",
        localisation = "Metz",
        date = LocalDate.now(),
        description = "Tests de santé")
    AnimalEventCard(animalEvent)
}
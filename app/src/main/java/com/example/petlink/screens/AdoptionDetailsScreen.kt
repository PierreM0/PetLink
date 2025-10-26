package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petlink.R
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.viewmodels.AnimalState

@Composable
fun AdoptionDetailsScreen(animalState: AnimalState, onGoBack: () -> Unit) {
    val animal = animalState.selectedAnimal

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
            .padding(16.dp)
    ) {
        IconButton(onClick = onGoBack) {
            Icon(
                painter = painterResource(R.drawable.ic_return),
                contentDescription = "Return",
                modifier = Modifier.size(32.dp),
                tint = MainGreen
            )
        }
        animal?.let { animal ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = animal.imageUrl,
                    contentDescription = "Image de ${animal.name}",
                    modifier = Modifier.size(256.dp)
                )
            }
        } ?: run {
            Text("Aucun animal à afficher")
        }
    }
}
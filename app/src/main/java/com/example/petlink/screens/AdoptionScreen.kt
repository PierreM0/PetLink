package com.example.petlink.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.AnimalCard
import com.example.petlink.components.Dropdown
import com.example.petlink.components.SearchBar
import com.example.petlink.model.Animal
import com.example.petlink.ui.theme.BackgroundGreen
import androidx.core.net.toUri

@Composable
fun AdoptionScreen() {
    var locationSearchValue by remember { mutableStateOf(TextFieldValue("")) }
    var speciesValue by remember { mutableStateOf("Toutes espèces") }
    var ageRangeValue by remember { mutableStateOf("Tous âges")}

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
                onValueChange = { value -> locationSearchValue = value},
                placeholderText = "Rechercher par localisation",
                isLocationSearch = true)

            // Filtres espèces & âges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filtre espèces // TODO rendre meilleur les filtres pour les VMs
                Dropdown(value = speciesValue,
                    onValueChange = { value -> speciesValue = value },
                    items = listOf("Toutes espèces", "Chats", "Chiens", "Lapins"),
                    modifier = Modifier.weight(1f))

                // Filtre âges // TODO rendre meilleur les filtres pour les VMs
                Dropdown(value = ageRangeValue,
                    onValueChange = { value -> ageRangeValue = value },
                    items = listOf("Tous âges", "Bébé", "Adulte", "Senior"),
                    modifier = Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(32.dp))

        // TODO à remove
        val animal = Animal(id = 0, imageUrl = "https://cataas.com/cat?type=square",
            description = "Un chat mignon", name = "Maxou", location = "Metz",
            refuge = "Refuge lambda", species = "Chat", age = 5, refugeNumber = "".toUri())

        // Nombre de résultats // TODO vrai nb de résultats
        Text(text = "5 résultats", fontSize = 24.sp, fontWeight = FontWeight.Medium)

        Spacer(Modifier.height(16.dp))

        // Résultats (les cards d'animaux) // TODO vrais animaux à filtrer
        LazyColumn {
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
            item {
                AnimalCard(animal)
            }
        }
    }
}
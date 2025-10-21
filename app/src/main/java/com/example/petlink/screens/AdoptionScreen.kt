package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.petlink.components.Dropdown
import com.example.petlink.components.SearchBar
import com.example.petlink.ui.theme.BackgroundGreen

@Composable
fun AdoptionScreen() {
    var locationSearchValue by remember { mutableStateOf(TextFieldValue("")) }
    var speciesValue by remember { mutableStateOf("Toutes espèces") }

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundGreen)
            .padding(24.dp)
    ) {
        SearchBar(value = locationSearchValue,
            onValueChange = { value -> locationSearchValue = value},
            placeholderText = "Rechercher par localisation",
            isLocationSearch = true)

        Dropdown(value = speciesValue,
            onValueChange = { value -> speciesValue = value })
    }
}
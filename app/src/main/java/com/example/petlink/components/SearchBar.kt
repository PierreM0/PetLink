package com.example.petlink.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.petlink.ui.theme.White

@Composable
fun SearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit,
              placeholderText: String, isLocationSearch: Boolean) {
    TextField(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholderText) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White
        ),
        leadingIcon = { if (isLocationSearch) Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Localisation")},
        trailingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "Rechercher")}
    )
}
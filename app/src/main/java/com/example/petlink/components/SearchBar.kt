package com.example.petlink.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchBar(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit,
              placeholderText: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholderText) }
    )
}
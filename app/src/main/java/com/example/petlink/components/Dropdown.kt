package com.example.petlink.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Dropdown(items: List<String> = listOf("Toutes espèces", "Chats", "Chiens", "Lapins")) { // TODO remove default param
    var isExpanded by remember { mutableStateOf(false) }
    var itemPosition by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(items[itemPosition])
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        isExpanded = false
                        itemPosition = index
                    }
                )
            }
        }
    }
}
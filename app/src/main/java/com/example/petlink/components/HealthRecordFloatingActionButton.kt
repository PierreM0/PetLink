package com.example.petlink.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen

@Composable
fun HealthRecordFloatingActionButton(
    onAddAnimal: () -> Unit,
    onAddEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { showBottomSheet = true },
        modifier = modifier,
        containerColor = BackgroundGreen,
        shape = RoundedCornerShape(32.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Ajouter",
            modifier = Modifier.size(48.dp),
            tint = MainGreen
        )
    }
}
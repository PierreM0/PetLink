package com.example.petlink.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petlink.model.Veterinary
import com.example.petlink.ui.theme.White

@Composable
fun VeterinaryCard(veterinary: Veterinary) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().height(150.dp),
        colors = CardDefaults.cardColors(White),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row {
            Text(veterinary.name)
        }
    }
}
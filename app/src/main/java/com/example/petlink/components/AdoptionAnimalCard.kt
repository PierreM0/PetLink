package com.example.petlink.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.model.AdoptionAnimal
import com.example.petlink.ui.theme.SecondaryText
import com.example.petlink.ui.theme.White

@Composable
fun AnimalCard(adoptionAnimal: AdoptionAnimal) {
    ElevatedCard (
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = adoptionAnimal.imageUrl,
                contentDescription = "Photo de ${adoptionAnimal.name}",
                modifier = Modifier.size(128.dp)
                    .clip(RoundedCornerShape(32.dp))
            )

            Column(
                modifier = Modifier.height(128.dp)
            ) {
                // Nom de l'animal
                Text(text = adoptionAnimal.name, fontSize = 24.sp, fontWeight = FontWeight.Medium)

                Spacer(Modifier.height(4.dp))

                // Son espèce
                Text(text = adoptionAnimal.species, fontSize = 16.sp, color = SecondaryText)

                Spacer(Modifier.height(8.dp))

                // Âge de l'animal
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.DateRange, contentDescription = Icons.Outlined.DateRange.toString())
                    Text(text = "${adoptionAnimal.age} ans", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(Modifier.height(8.dp))

                // Localisation
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Outlined.Place, contentDescription = Icons.Outlined.Place.toString())
                    Text(text = adoptionAnimal.location, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

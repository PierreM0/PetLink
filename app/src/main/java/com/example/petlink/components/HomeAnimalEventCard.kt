package com.example.petlink.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.R
import com.example.petlink.model.AnimalEvent
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.SubGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.utils.toSlashString

@Composable
fun HomeAnimalEventCard(
    animalEvent: AnimalEvent
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(BackgroundGreen)
                    .size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_visit),
                    contentDescription = "Visite",
                    tint = MainGreen,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = animalEvent.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "${animalEvent.date.toSlashString()} - ${animalEvent.localisation}",
                    color = SubGreen
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_paw),
                        contentDescription = R.drawable.ic_paw.toString()
                    )

                    Text(text = animalEvent.animal.name)
                }
            }
        }
    }
}
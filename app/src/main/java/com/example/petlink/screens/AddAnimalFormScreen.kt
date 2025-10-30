package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.RawButton
import com.example.petlink.model.Animal
import com.example.petlink.ui.theme.FormPreviousGray
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White

@Composable
fun AddAnimalFormScreen(
    onAdd: (Animal) -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "Photo",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        // TODO input field (image)

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Nom",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        // TODO input field (nom)

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Espèce",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        // TODO input field (espèce)

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Date de naissance",
            fontSize = 16.sp,
            color = MainGreen
        )

        // TODO input field (date de naissance)

        Row {
            RawButton(onClick = {}) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(FormPreviousGray)
                        .width(150.dp)
                        .height(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Annuler",
                        fontSize = 24.sp,
                        color = MainGreen
                    )
                }
            }

            RawButton(onClick = onCancel) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(MainGreen)
                        .width(150.dp)
                        .height(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Ajouter",
                        fontSize = 24.sp,
                        color = White
                    )
                }
            }
        }
    }
}
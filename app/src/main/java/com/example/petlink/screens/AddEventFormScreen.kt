package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.Dropdown
import com.example.petlink.components.InputDateField
import com.example.petlink.components.InputTextField
import com.example.petlink.components.RawButton
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalEvent
import com.example.petlink.model.AnimalEventType
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.FormPreviousGray
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.HealthRecordState
import java.time.LocalDate

@Composable
fun AddEventFormScreen(
    state: HealthRecordState,
    onAdd: (AnimalEvent) -> Unit,
    onCancel: () -> Unit
) {
    var selectedTypeIndex by remember { mutableIntStateOf(0) }
    var title by remember { mutableStateOf(TextFieldValue(""))}
    var location by remember { mutableStateOf(TextFieldValue(""))}
    var date by remember { mutableStateOf<LocalDate?>(null)}
    var description by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
            .padding(24.dp)
    ) {
        Row {
            Text(
                text = "Type d'événement",
                fontSize = 16.sp,
                color = MainGreen
            )

            Text(
                text = " *",
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(8.dp))

        Dropdown(
            selectedIndex = selectedTypeIndex,
            onSelectedIndex = { selectedTypeIndex = it },
            items = AnimalEventType.entries.map { it.displayName }
        )

        Spacer(Modifier.height(32.dp))

        Row {
            Text(
                text = "Titre",
                fontSize = 16.sp,
                color = MainGreen
            )

            Text(
                text = " *",
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(8.dp))

        InputTextField(
            value = title,
            onValueChange = { title = it }
        )

        Spacer(Modifier.height(32.dp))

        Row {
            Text(
                text = "Localisation",
                fontSize = 16.sp,
                color = MainGreen
            )

            Text(
                text = " *",
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(8.dp))

        InputTextField(
            value = location,
            onValueChange = { location = it }
        )

        Spacer(Modifier.height(32.dp))

        Row {
            Text(
                text = "Date",
                fontSize = 16.sp,
                color = MainGreen
            )

            Text(
                text = " *",
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(8.dp))

        InputDateField(
            selectedDate = date,
            onDateSelected = { date = it }
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Description",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        InputTextField(
            value = description,
            onValueChange = { description = it }
        )

        Spacer(Modifier.weight(1f))

        val isFormValid = title.text.isNotBlank() && location.text.isNotBlank()
                && date != null

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RawButton(onClick = onCancel) {
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

            RawButton(
                onClick = {
                    val event = AnimalEvent(
                        type = AnimalEventType.entries[selectedTypeIndex],
                        title = title.text,
                        localisation = location.text,
                        date = date!!,
                        description = description.text
                    )
                    onAdd(event)
                },
                enabled = isFormValid
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(color =
                            if (isFormValid) MainGreen
                            else MainGreen.copy(alpha = 0.3f))
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
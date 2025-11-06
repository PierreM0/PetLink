package com.example.petlink.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
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
import com.example.petlink.components.FormButtons
import com.example.petlink.components.InputDateField
import com.example.petlink.components.InputImageField
import com.example.petlink.components.InputTextField
import com.example.petlink.components.RawButton
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalSpecies
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.FormPreviousGray
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAnimalFormScreen(
    onAdd: (Animal) -> Unit,
    onCancel: () -> Unit
) {
    var pictureUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var speciesIndex: Int by remember { mutableIntStateOf(0) }
    var species by remember { mutableStateOf(AnimalSpecies.entries[speciesIndex])}
    var birthDate by remember { mutableStateOf<LocalDate?>(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
            .padding(24.dp)
    ) {
        Row {
            Text(
                text = "Photo",
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

        InputImageField(
            selectedImage = pictureUri,
            onImageSelected = { uri -> pictureUri = uri },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(32.dp))

        Row {
            Text(
                text = "Nom",
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
            value = name,
            onValueChange = { name = it }
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Espèce",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        Dropdown(
            selectedIndex = speciesIndex,
            onSelectedIndex = { index ->
                speciesIndex = index
                species = AnimalSpecies.entries[speciesIndex]
            },
            items = AnimalSpecies.entries.map { it.displayName }
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Date de naissance",
            fontSize = 16.sp,
            color = MainGreen
        )

        Spacer(Modifier.height(8.dp))

        InputDateField(
            selectedDate = birthDate,
            onDateSelected = { birthDate = it },
            maxDate = LocalDate.now()
        )

        Spacer(Modifier.weight(1f))

        val isFormValid = !name.text.isBlank() && pictureUri != null

        FormButtons(
            onPrevious = onCancel,
            onNext = {
                val animal = Animal(
                    name = name.text,
                    species = species,
                    birthDate = birthDate,
                    pictureUri = pictureUri!!
                )
                onAdd(animal)
            },
            prevButtonText = "Annuler",
            nextButtonText = "Ajouter",
            nextButtonEnabled = isFormValid
        )
    }
}
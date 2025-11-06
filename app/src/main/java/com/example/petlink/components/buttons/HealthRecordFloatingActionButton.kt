package com.example.petlink.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.Black
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.viewmodels.HealthRecordViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthRecordFloatingActionButton(
    viewModel: HealthRecordViewModel,
    onAddAnimal: () -> Unit,
    onAddEvent: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.stateFlow.collectAsState().value

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    FloatingActionButton(
        onClick = { showBottomSheet = true },
        modifier = modifier
            .border(4.dp, MainGreen, RoundedCornerShape(32.dp)),
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

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)
            ) {
                RawButton(onClick = {
                    scope.launch {
                        sheetState.hide()
                        showBottomSheet = false
                    }
                    onAddAnimal()
                }) {
                    Text(
                        text = "Ajouter un animal",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                RawButton(
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                        }
                        onAddEvent()
                    },
                    enabled = state.selectedAnimal != null
                ) {
                    Text(
                        text = "Ajouter un événement",
                        fontSize = 24.sp,
                        color =
                            if (state.selectedAnimal != null) Black
                            else Black.copy(alpha = 0.3f),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
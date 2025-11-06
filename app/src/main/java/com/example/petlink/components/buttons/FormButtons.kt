package com.example.petlink.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.ui.theme.FormPreviousGray
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White

@Composable
fun FormButtons(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    prevButtonText: String,
    nextButtonText: String,
    nextButtonEnabled: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RawButton(onClick = onPrevious) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(FormPreviousGray)
                    .width(150.dp)
                    .height(44.dp)
                    .border(
                        width = 2.dp,
                        color = MainGreen,
                        shape = RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = prevButtonText,
                    fontSize = 24.sp,
                    color = MainGreen
                )
            }
        }

        RawButton(
            onClick = onNext,
            enabled = nextButtonEnabled
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(color =
                        if (nextButtonEnabled) MainGreen
                        else MainGreen.copy(alpha = 0.3f))
                    .width(150.dp)
                    .height(44.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = nextButtonText,
                    fontSize = 24.sp,
                    color = White
                )
            }
        }
    }
}
package com.example.petlink.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.petlink.ui.theme.Black
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.SubGreen

@Composable
fun AnimalEventTab(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            tabs.forEachIndexed { index, title ->
                RawButton(onClick = {
                    onTabSelected(index)
                }) {
                    Text(
                        text = title,
                        color = if (selectedIndex == index) MainGreen else Black,
                        fontSize = 20.sp
                    )
                }
            }
        }

        BoxWithConstraints (
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(SubGreen)
            )

            val tabWidthPx = maxWidth / tabs.size
            val offset = selectedIndex * tabWidthPx

            Box(
                modifier = Modifier
                    .width(tabWidthPx)
                    .fillMaxHeight()
                    .offset(x = offset)
                    .background(MainGreen)
                    .clip(RoundedCornerShape(32.dp))
            )
        }
    }
}
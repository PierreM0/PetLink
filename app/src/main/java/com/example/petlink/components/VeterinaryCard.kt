package com.example.petlink.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.R
import com.example.petlink.model.Veterinary
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.SubGreen
import com.example.petlink.ui.theme.White
import androidx.core.net.toUri

@Composable
fun VeterinaryCard(veterinary: Veterinary) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        colors = CardDefaults.cardColors(White),
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = veterinary.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
            ) {
                AsyncImage(
                    model = veterinary.imageUrl,
                    contentDescription = "Image of article ${veterinary.name}",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(140.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Spacer( modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.Place,
                            contentDescription = Icons.Outlined.Place.toString(),
                            tint = MainGreen
                        )
                        Text(
                            text = veterinary.city,
                            fontSize = 16.sp,
                            color = SubGreen
                        )
                    }
                    Spacer( modifier = Modifier.height(16.dp))
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = Icons.Outlined.DateRange.toString(),
                            tint = MainGreen
                        )
                        Text(
                            text = "Ouvert de ${veterinary.openingHour}h à ${veterinary.closingHour}h",
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val ctx = LocalContext.current
                Button(
                    modifier = Modifier.width(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = MainGreen
                    ),
                    border = BorderStroke(2.dp, MainGreen),
                    onClick = {
                        val i = Intent(Intent.ACTION_DIAL, veterinary.phoneNumber)
                        try {
                            ctx.startActivity(i)
                        } catch(_: SecurityException) {
                            Toast.makeText(ctx,
                                "Une erreur est survenue.",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = Icons.Outlined.DateRange.toString(),
                        )
                        Text(
                            text = "Appeler",
                            fontSize = 20.sp,
                        )
                    }
                }
                Button(
                    modifier = Modifier.width(160.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainGreen,
                        contentColor = White
                    ),
                    onClick = {
                        val gmmIntentUri =
                            "geo:${veterinary.latitude},${veterinary.longitude}?q=${veterinary.latitude},${veterinary.longitude}(${
                                Uri.encode(veterinary.name)
                            })".toUri()
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        ctx.startActivity(mapIntent)
                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_near_me),
                            contentDescription = Icons.Outlined.DateRange.toString(),
                        )
                        Text(
                            text = "Itinéraire",
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        }
    }
}
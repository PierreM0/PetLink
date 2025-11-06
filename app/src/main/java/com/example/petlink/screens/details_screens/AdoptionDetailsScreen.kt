package com.example.petlink.screens.details_screens

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.R
import com.example.petlink.components.buttons.RawButton
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.SecondaryText
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.states.AdoptionAnimalState

@Composable
fun AdoptionDetailsScreen(adoptionAnimalState: AdoptionAnimalState, onGoBack: () -> Unit) {
    val animal = adoptionAnimalState.selectedAdoptionAnimal

    // Contenu principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
            .padding(16.dp)
    ) {
        // Bouton retour
        IconButton(onClick = onGoBack) {
            Icon(
                painter = painterResource(R.drawable.ic_return),
                contentDescription = "Return",
                modifier = Modifier.size(32.dp),
                tint = MainGreen
            )
        }

        // Détails de l'animal
        animal?.let { animal ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image
                AsyncImage(
                    model = animal.imageUrl,
                    contentDescription = "Image de ${animal.name}",
                    modifier = Modifier.size(256.dp)
                        .clip(RoundedCornerShape(32.dp))
                )

                Spacer(Modifier.height(8.dp))

                // Nom
                Text(
                    text = animal.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // Espèce
                Text(
                    text = animal.species,
                    fontSize = 20.sp,
                    color = SecondaryText
                )

                Spacer(Modifier.height(8.dp))

                // Description
                Text(
                    text = animal.description,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    textAlign = TextAlign.Justify
                )

                Spacer(Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Âge et icone
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = Icons.Outlined.DateRange.toString(),
                            modifier = Modifier.size(32.dp)
                        )
                        Text(text = "${animal.age} ans", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    }

                    // Localisation et icone
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Place,
                            contentDescription = Icons.Outlined.Place.toString(),
                            modifier = Modifier.size(32.dp)
                        )
                        Text(text = animal.location, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    }

                    // Refuge et icone
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = Icons.Outlined.Home.toString(),
                            modifier = Modifier.size(32.dp)
                        )
                        Text(text = animal.refuge, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    }
                }

                Spacer(Modifier.height(32.dp))

                // Bouton appeler le refuge (redirige vers le numeroteur)
                val ctx = LocalContext.current
                RawButton(onClick = {
                    val i = Intent(Intent.ACTION_DIAL, animal.refugeNumber)
                    try {
                        ctx.startActivity(i)
                    } catch(_: SecurityException) {
                        Toast.makeText(ctx,
                            "Une erreur est survenue.",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(MainGreen)
                            .width(256.dp)
                            .height(44.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Appeler le refuge",
                            fontSize = 24.sp,
                            color = White
                        )
                    }
                }
            }
        } ?: run {
            Text("Aucun animal à afficher")
        }
    }
}
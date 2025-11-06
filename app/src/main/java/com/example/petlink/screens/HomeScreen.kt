package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petlink.components.AdoptionAnimalCard
import com.example.petlink.components.ArticleCard
import com.example.petlink.components.RawButton
import com.example.petlink.model.AdoptionAnimal
import com.example.petlink.model.Article
import com.example.petlink.navigation.PetLinkScreens
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.AdoptionAnimalState
import com.example.petlink.viewmodels.AdoptionAnimalViewModel
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel

@Composable
fun HomeScreen(
    articleViewModel: ArticleViewModel,
    adoptionAnimalViewModel: AdoptionAnimalViewModel,
    onArticleDetails: (Article) -> Unit,
    onShowEverything: (String) -> Unit,
    onAnimalDetails: (AdoptionAnimal) -> Unit
) {

    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value
    val adoptionAnimalState: AdoptionAnimalState = adoptionAnimalViewModel.stateFlow.collectAsState().value

    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Événements à venir",
            fontSize = 20.sp
        )
        // TODO Faire les événement à venir avec la Card du carnet de santé
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(4) {
                ElevatedCard(
                    modifier = Modifier
                        .height(120.dp)
                        .width(300.dp),
                    colors = CardDefaults.cardColors(White),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Exemple")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Annonces récentes",
                fontSize = 20.sp
            )
            Text(
                text = "Voir tout >",
                color = MainGreen,
                modifier = Modifier.clickable(onClick = { onShowEverything(PetLinkScreens.AdoptionScreen.route) })
            )
        }
        if (adoptionAnimalState.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth().height(144.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val newestAnimals = adoptionAnimalViewModel.getNewestAnimals(3)
            LazyRow(
                modifier = Modifier.height(144.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(newestAnimals) { animal ->
                    RawButton(onClick = { onAnimalDetails(animal) }) {
                        AdoptionAnimalCard(animal)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Article le plus récent",
                fontSize = 20.sp
            )
            Text(
                text = "Voir tout >",
                color = MainGreen,
                modifier = Modifier.clickable(onClick = { onShowEverything(PetLinkScreens.BlogScreen.route) })
            )
        }
        if (articleState.isLoading) {
            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            Spacer(Modifier.weight(1f))
        } else {
            val latestArticle : Article? = articleViewModel.getNewestArticle()
            latestArticle?.let { article ->
                RawButton(onClick = { onArticleDetails(article) }) {
                        ArticleCard(article)
                }
            }
        }
    }
}
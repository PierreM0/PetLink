package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.petlink.components.ArticleCard
import com.example.petlink.components.RawButton
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel

@Composable
fun HomeScreen(articleViewModel: ArticleViewModel, onArticleDetails : (Article) -> Unit) {
    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value

    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Événements à venir",
            fontSize = 24.sp
        )
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
                fontSize = 24.sp
            )
            RawButton(onClick = {}) {
                Text(
                    text = "Voir tout >",
                    color = MainGreen
                )
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(3) {
                ElevatedCard(
                    modifier = Modifier
                        .height(120.dp)
                        .width(200.dp),
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
                text = "Article le plus récent",
                fontSize = 24.sp
            )
            RawButton(onClick = {}) {
                Text(
                    text = "Voir tout >",
                    color = MainGreen
                )
            }
        }
        if (articleState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
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
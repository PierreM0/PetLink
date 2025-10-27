package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petlink.components.ArticleCard
import com.example.petlink.components.RawButton
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel

@Composable
fun HomeScreen(articleViewModel: ArticleViewModel) {
    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value

    Column(
        modifier = Modifier
            .background(BackgroundGreen)
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row {
            Text("Événements à venir")
            RawButton(onClick = {}) {
                Text("Voir tout >")
            }
        }
        LazyRow {  }

        Row {
            Text("Annonces récentes")
            RawButton(onClick = {}) {
                Text("Voir tout >")
            }
        }
        LazyRow {  }

        if (articleState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val latestArticle : Article? = articleViewModel.getNewestArticle()
            RawButton(onClick = {}) {
                latestArticle?.let { article ->
                    ArticleCard(latestArticle)
                }
            }
        }
    }
}
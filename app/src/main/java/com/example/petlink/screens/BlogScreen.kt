package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.petlink.components.ArticleCard
import com.example.petlink.components.SearchBar
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.viewmodels.ArticleState

@Composable
fun BlogScreen(articleState : ArticleState) {
    var titleSearchValue by remember { mutableStateOf(TextFieldValue("") )}
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundGreen).padding(24.dp)
    ) {
        SearchBar(
            value = titleSearchValue,
            onValueChange = { value -> titleSearchValue = value},
            placeholderText = "Rechercher par titre",
            isLocationSearch = false
        )

        if (articleState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            val articles = articleState.articles
            val filteredList: List<Article> = articles.filter { it.title.lowercase().contains(titleSearchValue.text.lowercase()) }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredList) { article ->
                    //Button(onClick = { onDetails(article) }) {
                        ArticleCard(article)
                    //}
                }
            }
        }
    }
}
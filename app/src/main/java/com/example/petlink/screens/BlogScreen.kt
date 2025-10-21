package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.petlink.components.ArticleCard
import com.example.petlink.components.SearchBar
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.BackgroundGreen
import java.util.Date
import kotlin.random.Random

@Composable
fun BlogScreen() {
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
        // TODO faire avec ArticleState avec ktor
        var articles = mutableListOf<Article>()
        for(i in 1..5)
            articles.add( Article(1, Date(10),"Un superbe titre","Un superbe article","https://placedog.net/"+Random.nextInt(200, 801)))
        val filteredList: List<Article> = articles.filter {
            it.title.lowercase().contains(titleSearchValue.text.lowercase())
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // TODO faire que ça soit un bouton pour vers une vue détaillée
            items(filteredList) { article ->
                ArticleCard(article)
            }
        }
    }
}
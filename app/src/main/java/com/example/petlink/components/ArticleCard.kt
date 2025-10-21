package com.example.petlink.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petlink.model.Article
import coil.compose.AsyncImage

@Composable
fun ArticleCard(article : Article) {
    Column(
        modifier = Modifier.fillMaxSize().height(40.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = "Image of article ${article.title}",
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )
        Text(article.title)
        Text(article.content)

    }
}
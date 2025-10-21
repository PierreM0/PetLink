package com.example.petlink.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.White

@Composable
fun ArticleCard(article : Article) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(White),
        shape = RoundedCornerShape(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = "Image of article ${article.title}",
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(16.dp)
                .height(50.dp)
        ) {
            Text(article.title)
            Text(article.content)
        }
    }
}
package com.example.petlink.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.White

@Composable
fun ArticleCard(article : Article) {
    Card(
        modifier = Modifier.fillMaxWidth().height(150.dp),
        colors = CardDefaults.cardColors(White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = "Image of article ${article.title}",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(150.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = article.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                    )
                Text(
                    text = article.content,
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.fillMaxSize(),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                    )
            }
        }
    }
}
package com.example.petlink.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.model.Article
import com.example.petlink.ui.theme.White

@Composable
fun ArticleCard(article : Article) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().height(150.dp),
        colors = CardDefaults.cardColors(White),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = "Image of article ${article.title}",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = article.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                    )
                Text(
                    text = article.content,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
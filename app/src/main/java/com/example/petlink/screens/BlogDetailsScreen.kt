package com.example.petlink.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.petlink.R
import com.example.petlink.ui.theme.BackgroundGreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.ArticleState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogDetailsScreen(articleState: ArticleState, onGoBack : () -> Unit) {
    val article = articleState.selectedArticle

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreen)
            .padding(horizontal = 24.dp, )
    ) {
        IconButton(onClick = onGoBack) {
            Icon(
                painter = painterResource(R.drawable.ic_return),
                contentDescription = "Return",
                modifier = Modifier.size(32.dp),
                tint = MainGreen
            )
        }
        article?.let { article ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 24.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))
                        .background(White)
                ) {
                    AsyncImage(
                        model = article.imageUrl,
                        contentDescription = "Image of ${article.title}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(256.dp),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = article.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = article.content,
                            textAlign = TextAlign.Justify,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        } ?: run {
            Text("Aucun article à afficher")
        }
    }
}
package ufr.mim.netfloux.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.petlink.viewmodels.ArticleState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogDetailsScreen(articleState: ArticleState) {
    val article = articleState.selectedArticle
    Text(text = article?.title ?: "Y'a pas de selectedArticle")
}
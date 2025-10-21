package com.example.petlink.screens

import androidx.compose.runtime.Composable
import com.example.petlink.components.ArticleCard
import com.example.petlink.model.Article
import java.util.Date

@Composable
fun BlogScreen() {
    var art : Article = Article(1, Date(10),"Un superbe titre","Un superbe article","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSGxwl4fhem6m2urbLPsROL7UHP2tjF4CowszALXe5IwDIMG7C2VdCqSyLCsNi4mrqWuKJgVE215SQCpX3TOTQSUgwhOnN_kBr2FB6omLvWww")
    ArticleCard(art)
}
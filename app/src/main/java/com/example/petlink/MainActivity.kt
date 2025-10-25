package com.example.petlink

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.petlink.navigation.PetLinkNavigationComponent
import com.example.petlink.ui.theme.PetLinkTheme
import com.example.petlink.viewmodels.AnimalViewModel
import com.example.petlink.viewmodels.ArticleViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetLinkTheme {
                val articleViewModel = ArticleViewModel()
                PetLinkNavigationComponent(articleViewModel)
            }
        }
    }
}
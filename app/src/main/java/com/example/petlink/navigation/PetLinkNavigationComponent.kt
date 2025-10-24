package com.example.petlink.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petlink.components.BottomNavigationBar
import com.example.petlink.screens.AdoptionScreen
import com.example.petlink.screens.BlogDetailsScreen
import com.example.petlink.screens.BlogScreen
import com.example.petlink.viewmodels.AnimalState
import com.example.petlink.viewmodels.AnimalViewModel
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel

@SuppressLint("RestrictedApi")
@Composable
fun PetLinkNavigationComponent(articleViewModel: ArticleViewModel,
                               animalViewModel: AnimalViewModel) {
    val navController = rememberNavController()

    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value
    val animalState: AnimalState = animalViewModel.stateFlow.collectAsState().value

    LaunchedEffect(articleState.selectedArticle) {
        if (articleState.selectedArticle != null) {
            navController.navigate(PetLinkScreens.BlogDetailsScreen.name)
        }
    }

    /*
    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            // backQueue is public in 2.9.x — still valid
            val stackEntries = controller.currentBackStack.value
                .filter { it.destination.route != null } // ignore graph-level entries
            val stackSize = stackEntries.size

            Log.d("PetLinkNav", "🧭 Route: ${destination.route}, Stack size: $stackSize")

            stackEntries.forEachIndexed { index, entry ->
                Log.d("PetLinkNav", "   [$index] ${entry.destination.route}")
            }
        }
    }
    */

    Scaffold(
        bottomBar = { BottomNavigationBar(onIconClick = { route ->
            navController.popBackStack(route, true)
            navController.navigate(route)
        }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = PetLinkScreens.HomeScreen.route) {
                composable(PetLinkScreens.HomeScreen.route) {
                    Text(PetLinkScreens.HomeScreen.title)
                }

                composable(PetLinkScreens.AdoptionScreen.route) {
                    AdoptionScreen(animalState)
                }

                composable(PetLinkScreens.HealthScreen.route) {
                    Text(PetLinkScreens.HealthScreen.title)
                }

                composable(PetLinkScreens.BlogScreen.route) {
                    BlogScreen(articleState,
                        onDetails = { article ->
                            articleViewModel.setSelectedArticle(article)
                        })
                }

                composable(PetLinkScreens.VeterinaryScreen.route) {
                    Text(PetLinkScreens.VeterinaryScreen.title)
                }

                composable(PetLinkScreens.BlogDetailsScreen.name) {
                    BlogDetailsScreen(articleState)
                }
            }
        }
    }
}

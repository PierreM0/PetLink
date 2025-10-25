package com.example.petlink.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petlink.components.BottomNavigationBar
import com.example.petlink.screens.AdoptionScreen
import com.example.petlink.screens.BlogDetailsScreen
import com.example.petlink.screens.BlogScreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RestrictedApi")
@Composable
fun PetLinkNavigationComponent(articleViewModel: ArticleViewModel) {
    val navController = rememberNavController()

    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value

    var topBarName by remember { mutableStateOf("Accueil - PetLink") }

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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topBarName,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainGreen
                )
            )
        },
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
                    topBarName = PetLinkScreens.HomeScreen.title
                }

                composable(PetLinkScreens.AdoptionScreen.route) {
                    topBarName = PetLinkScreens.AdoptionScreen.title
                    AdoptionScreen()
                }

                composable(PetLinkScreens.HealthScreen.route) {
                    topBarName = PetLinkScreens.HealthScreen.title
                }

                composable(PetLinkScreens.BlogScreen.route) {
                    topBarName = PetLinkScreens.BlogScreen.title
                    BlogScreen(articleState,
                        onDetails = { article ->
                            articleViewModel.setSelectedArticle(article)
                            navController.navigate(PetLinkScreens.BlogDetailsScreen.name)
                        })
                }

                composable(PetLinkScreens.VeterinaryScreen.route) {
                    topBarName = PetLinkScreens.VeterinaryScreen.title
                }

                composable(PetLinkScreens.BlogDetailsScreen.name) {
                    BlogDetailsScreen(articleState, onGoBack = { navController.popBackStack() })
                }
            }
        }
    }
}

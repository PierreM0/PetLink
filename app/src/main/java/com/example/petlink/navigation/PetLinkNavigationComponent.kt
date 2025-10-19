package com.example.petlink.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petlink.components.BottomNavigationBar

@Composable
fun PetLinkNavigationComponent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(onIconClick = { route ->
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
                    Text(PetLinkScreens.AdoptionScreen.title)
                }

                composable(PetLinkScreens.HealthScreen.route) {
                    Text(PetLinkScreens.HealthScreen.title)
                }

                composable(PetLinkScreens.BlogScreen.route) {
                    Text(PetLinkScreens.BlogScreen.title)
                }

                composable(PetLinkScreens.VeterinaryScreen.route) {
                    Text(PetLinkScreens.VeterinaryScreen.title)
                }
            }
        }
    }
}

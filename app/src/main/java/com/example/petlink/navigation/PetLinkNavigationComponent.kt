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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.petlink.components.BottomNavigationBar
import com.example.petlink.screens.AddAnimalFormScreen
import com.example.petlink.screens.AddEventFormScreen
import com.example.petlink.screens.AdoptionDetailsScreen
import com.example.petlink.screens.AdoptionScreen
import com.example.petlink.screens.BlogDetailsScreen
import com.example.petlink.screens.BlogScreen
import com.example.petlink.screens.HealthRecordScreen
import com.example.petlink.screens.HomeScreen
import com.example.petlink.screens.VeterinaryScreen
import com.example.petlink.ui.theme.MainGreen
import com.example.petlink.ui.theme.White
import com.example.petlink.viewmodels.AdoptionAnimalState
import com.example.petlink.viewmodels.AdoptionAnimalViewModel
import com.example.petlink.viewmodels.ArticleState
import com.example.petlink.viewmodels.ArticleViewModel
import com.example.petlink.viewmodels.HealthRecordState
import com.example.petlink.viewmodels.HealthRecordViewModel
import com.example.petlink.viewmodels.VeterinaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RestrictedApi")
@Composable
fun PetLinkNavigationComponent() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val adoptionAnimalViewModel: AdoptionAnimalViewModel = viewModel()
    val healthRecordViewModel: HealthRecordViewModel = viewModel()
    val articleViewModel: ArticleViewModel = viewModel()
    val veterinaryViewModel: VeterinaryViewModel = viewModel()

    val adoptionAnimalState: AdoptionAnimalState = adoptionAnimalViewModel.stateFlow.collectAsState().value
    val healthRecordState: HealthRecordState = healthRecordViewModel.stateFlow.collectAsState().value
    val articleState: ArticleState = articleViewModel.stateFlow.collectAsState().value

    var topBarName by remember { mutableStateOf(PetLinkScreens.HomeScreen.title) }

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
        bottomBar = { BottomNavigationBar(
            currentRoute = currentRoute,
            onIconClick = { route ->
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
                    HomeScreen(healthRecordViewModel,
                        articleViewModel,
                        adoptionAnimalViewModel,
                        onArticleDetails = { article ->
                            articleViewModel.setSelectedArticle(article)
                            navController.navigate(PetLinkScreens.BlogDetailsScreen.route)
                        },
                        onAnimalDetails = { animal ->
                            adoptionAnimalViewModel.setSelectedAnimal(animal)
                            navController.navigate(PetLinkScreens.AdoptionDetailsScreen.route)
                        },
                        onShowEverything = { route ->
                            navController.navigate(route)
                        }
                    )
                }

                composable(PetLinkScreens.AdoptionScreen.route) {
                    topBarName = PetLinkScreens.AdoptionScreen.title
                    AdoptionScreen(adoptionAnimalViewModel,
                        onDetails = { animal ->
                            adoptionAnimalViewModel.setSelectedAnimal(animal)
                            navController.navigate(PetLinkScreens.AdoptionDetailsScreen.route)
                        })
                }

                composable(PetLinkScreens.AdoptionDetailsScreen.route) {
                    AdoptionDetailsScreen(
                        adoptionAnimalState = adoptionAnimalState,
                        onGoBack = { navController.popBackStack() }
                    )
                }

                composable(PetLinkScreens.HealthRecordScreen.route) {
                    topBarName = PetLinkScreens.HealthRecordScreen.title
                    HealthRecordScreen(
                        healthRecordViewModel,
                        onAddAnimal = {
                            navController.navigate(PetLinkScreens.AddAnimalFormScreen.route)
                        },
                        onAddEvent = {
                            navController.navigate(PetLinkScreens.AddEventFormScreen.route)
                        }
                    )
                }

                composable(PetLinkScreens.AddAnimalFormScreen.route) {
                    topBarName = PetLinkScreens.AddAnimalFormScreen.title
                    AddAnimalFormScreen(
                        onAdd = { animal ->
                            healthRecordViewModel.addAnimal(animal)
                            navController.popBackStack()
                        },
                        onCancel = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(PetLinkScreens.AddEventFormScreen.route) {
                    topBarName = PetLinkScreens.AddEventFormScreen.title
                    AddEventFormScreen(
                        healthRecordState,
                        onAdd = { event ->
                            healthRecordViewModel.addEventToSelectedAnimal(event)
                            navController.popBackStack()
                        },
                        onCancel = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(PetLinkScreens.BlogScreen.route) {
                    topBarName = PetLinkScreens.BlogScreen.title
                    BlogScreen(articleState,
                        onDetails = { article ->
                            articleViewModel.setSelectedArticle(article)
                            navController.navigate(PetLinkScreens.BlogDetailsScreen.route)
                        })
                }

                composable(PetLinkScreens.BlogDetailsScreen.route) {
                    BlogDetailsScreen(articleState, onGoBack = { navController.popBackStack() })
                }

                composable(PetLinkScreens.VeterinaryScreen.route) {
                    topBarName = PetLinkScreens.VeterinaryScreen.title
                    VeterinaryScreen(veterinaryViewModel)
                }
            }
        }
    }
}

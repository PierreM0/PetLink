package com.example.petlink.navigation

enum class PetLinkScreens(val route: String, val title: String) {
    HomeScreen("home", "PetLink"),
    AdoptionScreen("adoption", "Espace adoption"),
    HealthScreen("health", "Carnet de santé"),
    BlogScreen("blog", "Blog"),
    VeterinaryScreen("veterinary", "Vétérinaires")
}
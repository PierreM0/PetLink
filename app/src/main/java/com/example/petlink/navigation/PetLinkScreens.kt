package com.example.petlink.navigation

enum class PetLinkScreens(val route: String, val title: String) {
    HomeScreen("home", "PetLink"),
    AdoptionScreen("adoption", "Espace adoption"),
    AdoptionDetailsScreen("adoptionDetails", "Espace adoption"),
    HealthRecordScreen("health", "Carnet de santé"),
    AddAnimalFormScreen("add_animal", "Nouvel animal"),
    AddEventFormScreen("add_event", "Nouvel événement"),
    BlogScreen("blog", "Blog"),
    BlogDetailsScreen("blogDetails", "BlogDetails"),
    VeterinaryScreen("veterinary", "Vétérinaires")
}
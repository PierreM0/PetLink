package com.example.petlink.model

enum class AnimalSpecies(val displayName: String) {
    All("Toutes espèces"),
    Cat("Chat"),
    Dog("Chien"),
    Bunny("Lapin");

    companion object {
        fun fromDisplayName(name: String): AnimalSpecies {
            return entries.find { it.displayName == name } ?: All
        }

        fun getAllDisplayNames(): List<String> {
            return entries.map { it.displayName }
        }
    }

    fun matches(name: String): Boolean {
        return if (this == All)
            true
        else
            name == this.displayName
    }
}
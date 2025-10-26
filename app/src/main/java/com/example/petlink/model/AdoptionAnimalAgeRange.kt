package com.example.petlink.model

enum class AdoptionAnimalAgeRange(val displayName: String, val minAge: Int, val maxAge: Int) {
    All("Tous âges", 0, Int.MAX_VALUE),
    Baby("Bébé (0-2 ans)", 0, 2),
    Adult("Adulte (3-8 ans)", 3, 8),
    Senior("Senior (9+ ans)", 9, Int.MAX_VALUE);

    companion object {
        fun fromDisplayName(name: String): AdoptionAnimalAgeRange {
            return entries.find { it.displayName == name } ?: All
        }

        fun getAllDisplayNames(): List<String> {
            return entries.map { it.displayName }
        }
    }

    fun matches(age: Int): Boolean {
        return age in this.minAge..this.maxAge
    }
}
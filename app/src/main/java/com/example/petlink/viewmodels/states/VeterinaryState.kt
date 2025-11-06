package com.example.petlink.viewmodels.states

import com.example.petlink.model.Veterinary

data class VeterinaryState (
    val veterinaries: List<Veterinary> = listOf(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)
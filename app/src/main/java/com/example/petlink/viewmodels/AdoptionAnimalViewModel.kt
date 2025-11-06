package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.model.adoption_animal.AdoptionAnimal
import com.example.petlink.model.adoption_animal.AdoptionAnimalAgeRange
import com.example.petlink.model.adoption_animal.AdoptionAnimalSpecies
import com.example.petlink.network.StateManager
import com.example.petlink.viewmodels.states.AdoptionAnimalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdoptionAnimalViewModel : ViewModel() {
    private val animalsMutableStateFlow = MutableStateFlow(AdoptionAnimalState())

    val stateFlow: StateFlow<AdoptionAnimalState>
        get() = animalsMutableStateFlow.asStateFlow()

    private var AdoptionAnimalState: AdoptionAnimalState
        get() = animalsMutableStateFlow.value
        set(value) {
            animalsMutableStateFlow.value = value
        }

    init {
        viewModelScope.launch {
            getAnimalList()
        }
    }

    fun getAnimalList() {
        AdoptionAnimalState = AdoptionAnimalState.copy(isLoading = true)
        StateManager.launchCoroutine {
            AdoptionAnimalState = try {
                AdoptionAnimalState.copy(
                    adoptionAnimals = PetLinkAPI.getAnimals(),
                    isLoading = false
                )
            } catch (error: Exception) {
                AdoptionAnimalState.copy(
                    error = error,
                    isLoading = false
                )
            }
        }
    }

    fun setSelectedAnimal(adoptionAnimal: AdoptionAnimal) {
        AdoptionAnimalState = AdoptionAnimalState.copy(
            selectedAdoptionAnimal = adoptionAnimal
        )
    }

    fun setLocationFilter(location: String) {
        AdoptionAnimalState = AdoptionAnimalState.copy(
            filters = AdoptionAnimalState.filters.copy(
                location = location
            )
        )
    }

    fun setSpeciesFilter(filter: AdoptionAnimalSpecies) {
        AdoptionAnimalState = AdoptionAnimalState.copy(
            filters = AdoptionAnimalState.filters.copy(
                species = filter
            )
        )
    }

    fun setAgeRangeFilter(filter: AdoptionAnimalAgeRange) {
        AdoptionAnimalState = AdoptionAnimalState.copy(
            filters = AdoptionAnimalState.filters.copy(
                ageRange = filter
            )
        )
    }

    fun getFilteredAnimals(): List<AdoptionAnimal> {
        return AdoptionAnimalState.adoptionAnimals.filter {
            it.location.lowercase().contains(AdoptionAnimalState.filters.location.lowercase())
        }.filter {
            AdoptionAnimalState.filters.species.matches(it.species)
        }.filter {
            AdoptionAnimalState.filters.ageRange.matches(it.age)
        }
    }

    /**
     * Retourne les n animaux les plus récemment ajoutés
     */
    fun getNewestAnimals(number: Int): List<AdoptionAnimal> {
        return AdoptionAnimalState.adoptionAnimals
            .sortedByDescending { it.publicationDate }
            .take(number)
    }
}
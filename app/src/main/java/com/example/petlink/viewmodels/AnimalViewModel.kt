package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalAgeRange
import com.example.petlink.model.AnimalSpecies
import com.example.petlink.network.StateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimalViewModel : ViewModel() {
    private val animalsMutableStateFlow = MutableStateFlow(AnimalState())

    val stateFlow: StateFlow<AnimalState>
        get() = animalsMutableStateFlow.asStateFlow()

    private var AnimalState: AnimalState
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
        AnimalState = AnimalState.copy(isLoading = true)
        StateManager.launchCoroutine {
            AnimalState = try {
                AnimalState.copy(
                    animals = PetLinkAPI.getAnimals(),
                    isLoading = false
                )
            } catch (error: Exception) {
                AnimalState.copy(
                    error = error,
                    isLoading = false
                )
            }
        }
    }

    fun setSelectedAnimal(animal: Animal) {
        AnimalState = AnimalState.copy(
            selectedAnimal = animal
        )
    }

    fun setLocationFilter(location: String) {
        AnimalState = AnimalState.copy(
            filters = AnimalState.filters.copy(
                location = location
            )
        )
    }

    fun setSpeciesFilter(species: String) {
        AnimalState = AnimalState.copy(
            filters = AnimalState.filters.copy(
                species = AnimalSpecies.fromDisplayName(species)
            )
        )
    }

    fun setAgeRangeFilter(ageRange: String) {
        AnimalState = AnimalState.copy(
            filters = AnimalState.filters.copy(
                ageRange = AnimalAgeRange.fromDisplayName(ageRange)
            )
        )
    }

    fun getFilteredAnimals(): List<Animal> {
        return AnimalState.animals.filter {
            it.location.lowercase().contains(AnimalState.filters.location.lowercase())
        }.filter {
            AnimalState.filters.species.matches(it.species)
        }.filter {
            AnimalState.filters.ageRange.matches(it.age)
        }
    }
}
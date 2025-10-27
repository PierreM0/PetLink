package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.model.Animal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HealthRecordViewModel : ViewModel() {
    private val healthRecordMutableStateFlow = MutableStateFlow(HealthRecordState())

    val stateFlow: StateFlow<HealthRecordState>
        get() = healthRecordMutableStateFlow.asStateFlow()

    private var HealthRecordState: HealthRecordState
        get() = healthRecordMutableStateFlow.value
        set(value) {
            healthRecordMutableStateFlow.value = value
        }

    init {
        viewModelScope.launch {
            val animal1 = Animal(name = "Noisette", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")
            val animal2 = Animal(name = "Noisette", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")
            val animal3 = Animal(name = "Noisette", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")
            addAnimal(animal1)
            addAnimal(animal2)
            addAnimal(animal3)
        }
    }

    fun addAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            animals = (HealthRecordState.animals + animal) as MutableList<Animal>
        )
    }

    fun setSelectedAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            selectedAnimal = animal
        )
    }
}
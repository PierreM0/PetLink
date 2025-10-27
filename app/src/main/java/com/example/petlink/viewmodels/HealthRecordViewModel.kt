package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import com.example.petlink.model.Animal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HealthRecordViewModel : ViewModel() {
    private val healthRecordMutableStateFlow = MutableStateFlow(HealthRecordState())

    val stateFlow: StateFlow<HealthRecordState>
        get() = healthRecordMutableStateFlow.asStateFlow()

    private var HealthRecordState: HealthRecordState
        get() = healthRecordMutableStateFlow.value
        set(value) {
            healthRecordMutableStateFlow.value = value
        }

    fun getAnimals(): MutableList<Animal> {
        return HealthRecordState.animals
    }

    fun setSelectedAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            selectedAnimal = animal
        )
    }
}
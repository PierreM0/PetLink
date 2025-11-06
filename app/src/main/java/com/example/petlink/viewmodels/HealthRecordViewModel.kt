package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import com.example.petlink.model.animal.Animal
import com.example.petlink.model.animal.AnimalEvent
import com.example.petlink.viewmodels.states.HealthRecordState
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

    fun addAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            animals = HealthRecordState.animals + animal
        )
    }

    fun addEventToSelectedAnimal(animalEvent: AnimalEvent) {
        HealthRecordState.selectedAnimal?.let { animal ->
            animal.events.add(animalEvent)
            HealthRecordState = HealthRecordState.copy()
        }
    }

    fun setSelectedAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            selectedAnimal = animal
        )
    }

    fun setSelectedTabIndex(index: Int) {
        HealthRecordState = HealthRecordState.copy(
            selectedTabIndex = index
        )
    }

    fun getUpcomingEvents(limit : Int) : List<AnimalEvent> {
        val events: MutableList<AnimalEvent> = mutableListOf()
        HealthRecordState.animals.forEach { animal ->
            animal.events.forEach { event ->
                events.add(event)
            }
        }

        return events.sortedBy { it.date }.take(limit)
    }
}
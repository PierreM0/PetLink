package com.example.petlink.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.model.Animal
import com.example.petlink.model.AnimalEvent
import com.example.petlink.model.AnimalEventType
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

    fun addAnimal(animal: Animal) {
        HealthRecordState = HealthRecordState.copy(
            animals = HealthRecordState.animals + animal
        )
    }

    fun addEventToSelectedAnimal(animalEvent: AnimalEvent) {
        HealthRecordState.selectedAnimal?.let { animal ->
            val updatedAnimal = animal.copy(events = animal.events + animalEvent)
            HealthRecordState = HealthRecordState.copy(
                animals = HealthRecordState.animals.map {
                    if (it.id == animal.id) updatedAnimal else it
                },
                selectedAnimal = updatedAnimal
            )
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
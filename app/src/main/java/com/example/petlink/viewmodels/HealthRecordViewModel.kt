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

    init {
        viewModelScope.launch {
            // TODO retirer les mock data
            val animal1 = Animal(name = "Noisette1", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")
            val animal2 = Animal(name = "Noisette2", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")
            val animal3 = Animal(name = "Noisette3", birthDate = LocalDate.now(),
                pictureUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=400&h=400&fit=crop")

            addAnimal(animal1)
            addAnimal(animal2)
            addAnimal(animal3)
            setSelectedAnimal(animal2)
            addEventToSelectedAnimal(AnimalEvent(AnimalEventType.Vaccine, "Rage", "Metz", LocalDate.now(),
                "Vaccin contre la rage à effectuer le plus tôt possible"))
            addEventToSelectedAnimal(AnimalEvent(AnimalEventType.Vaccine, "PQZ", "Metz", LocalDate.now(),
                "Vaccin PQZ pour protéger"))
            Log.d("Qasim", animal1.toString())
            Log.d("Qasim", animal2.toString())
            Log.d("Qasim", animal3.toString())
        }
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
}
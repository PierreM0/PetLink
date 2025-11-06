package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.network.StateManager
import com.example.petlink.viewmodels.states.VeterinaryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class VeterinaryViewModel: ViewModel() {
    private val veterinariesMutableStateFlow = MutableStateFlow(VeterinaryState())

    val stateFlow: StateFlow<VeterinaryState>
        get() = veterinariesMutableStateFlow.asStateFlow()

    private var veterinaryState: VeterinaryState
        get() = veterinariesMutableStateFlow.value
        set(value) {
            veterinariesMutableStateFlow.value = value
        }

    init {
        viewModelScope.launch {
            getVeterinariesList()
        }
    }

    fun getVeterinariesList() {
        veterinaryState = veterinaryState.copy(isLoading = true)
        StateManager.launchCoroutine {
            veterinaryState = try {
                veterinaryState.copy(
                    veterinaries = PetLinkAPI.getVeterinaries(),
                    isLoading = false
                )
            } catch (error: Exception) {
                veterinaryState.copy(
                    error = error,
                    isLoading = false
                )
            }
        }
    }

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371 // km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat/2).pow(2.0) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon/2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1-a))
        return r * c
    }
    suspend fun getLatAndLongFrom(cityName: String): Pair<Double, Double>? {
        return PetLinkAPI.getLatAndLongFrom(cityName)
    }
}
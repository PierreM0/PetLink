package com.example.petlink.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlink.api.PetLinkAPI
import com.example.petlink.network.StateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
}
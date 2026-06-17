package com.gymtracker.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.DashboardInfo
import com.gymtracker.domain.usecase.GetDashboardInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DashboardUiState {
    object Loading : DashboardUiState()
    data class Success(val info: DashboardInfo) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardInfo: GetDashboardInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        load()
        // Atualiza a hora a cada minuto
        viewModelScope.launch {
            while (true) {
                delay(60_000)
                load()
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            try {
                val info = getDashboardInfo()
                _uiState.value = DashboardUiState.Success(info)
            } catch (e: Exception) {
                _uiState.value = DashboardUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}

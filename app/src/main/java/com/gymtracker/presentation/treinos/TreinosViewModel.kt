package com.gymtracker.presentation.treinos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.DiaSemana
import com.gymtracker.domain.model.Treino
import com.gymtracker.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreinosViewModel @Inject constructor(
    private val getTreinos: GetTreinosUseCase,
    private val deleteTreino: DeleteTreinoUseCase,
    private val duplicarTreino: DuplicarTreinoUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val treinos: StateFlow<List<Treino>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isBlank()) getTreinos()
            else getTreinos() // search via filter for simplicity
        }
        .map { list ->
            if (_searchQuery.value.isBlank()) list
            else list.filter {
                it.nome.contains(_searchQuery.value, ignoreCase = true) ||
                it.grupoMuscular.contains(_searchQuery.value, ignoreCase = true)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val snackbarMessage = MutableStateFlow<String?>(null)

    fun setQuery(q: String) { _searchQuery.value = q }

    fun deletar(id: Long) {
        viewModelScope.launch {
            try {
                deleteTreino(id)
                snackbarMessage.value = "Treino excluído"
            } catch (e: Exception) {
                snackbarMessage.value = "Erro ao excluir: ${e.message}"
            }
        }
    }

    fun duplicar(id: Long) {
        viewModelScope.launch {
            try {
                duplicarTreino(id)
                snackbarMessage.value = "Treino duplicado"
            } catch (e: Exception) {
                snackbarMessage.value = "Erro ao duplicar: ${e.message}"
            }
        }
    }
}

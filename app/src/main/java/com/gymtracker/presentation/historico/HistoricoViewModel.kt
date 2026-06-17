package com.gymtracker.presentation.historico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.HistoricoTreino
import com.gymtracker.domain.usecase.GetHistoricoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoricoViewModel @Inject constructor(
    private val getHistorico: GetHistoricoUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val historico: StateFlow<List<HistoricoTreino>> = _query
        .debounce(300)
        .flatMapLatest { _ -> getHistorico() }
        .map { lista ->
            if (_query.value.isBlank()) lista
            else lista.filter {
                it.nomeTreino.contains(_query.value, ignoreCase = true)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setQuery(q: String) { _query.value = q }
}

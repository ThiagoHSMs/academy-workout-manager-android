package com.gymtracker.presentation.treinos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.DiaSemana
import com.gymtracker.domain.model.Treino
import com.gymtracker.domain.usecase.GetTreinoByIdUseCase
import com.gymtracker.domain.usecase.SaveTreinoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TreinoFormState(
    val id: Long = 0,
    val nome: String = "",
    val diaSemana: Int = DiaSemana.SEGUNDA.value,
    val grupoMuscular: String = "",
    val observacoes: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null,
    val nomeError: String? = null,
    val grupoError: String? = null
)

@HiltViewModel
class TreinoFormViewModel @Inject constructor(
    private val getTreinoById: GetTreinoByIdUseCase,
    private val saveTreino: SaveTreinoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TreinoFormState())
    val state: StateFlow<TreinoFormState> = _state.asStateFlow()

    fun carregarTreino(id: Long) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val treino = getTreinoById(id)
            if (treino != null) {
                _state.value = _state.value.copy(
                    id = treino.id,
                    nome = treino.nome,
                    diaSemana = treino.diaSemana,
                    grupoMuscular = treino.grupoMuscular,
                    observacoes = treino.observacoes,
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun onNomeChange(v: String) { _state.value = _state.value.copy(nome = v, nomeError = null) }
    fun onDiaChange(v: Int) { _state.value = _state.value.copy(diaSemana = v) }
    fun onGrupoChange(v: String) { _state.value = _state.value.copy(grupoMuscular = v, grupoError = null) }
    fun onObservacoesChange(v: String) { _state.value = _state.value.copy(observacoes = v) }

    fun salvar() {
        val s = _state.value
        var hasError = false
        if (s.nome.isBlank()) { _state.value = s.copy(nomeError = "Nome é obrigatório"); hasError = true }
        if (s.grupoMuscular.isBlank()) { _state.value = _state.value.copy(grupoError = "Grupo muscular é obrigatório"); hasError = true }
        if (hasError) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = saveTreino(
                Treino(id = s.id, nome = s.nome, diaSemana = s.diaSemana,
                    grupoMuscular = s.grupoMuscular, observacoes = s.observacoes)
            )
            result.fold(
                onSuccess = { _state.value = _state.value.copy(isSaved = true, isLoading = false) },
                onFailure = { _state.value = _state.value.copy(error = it.message, isLoading = false) }
            )
        }
    }
}

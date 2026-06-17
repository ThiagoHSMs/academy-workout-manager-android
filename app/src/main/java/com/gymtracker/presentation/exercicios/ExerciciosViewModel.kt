package com.gymtracker.presentation.exercicios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.Exercicio
import com.gymtracker.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── ExerciciosViewModel ──────────────────────────────────────────────────────

@HiltViewModel
class ExerciciosViewModel @Inject constructor(
    private val getExercicios: GetExerciciosUseCase,
    private val deleteExercicio: DeleteExercicioUseCase,
    private val reordenar: ReordenarExerciciosUseCase
) : ViewModel() {

    private val _treinoId = MutableStateFlow<Long?>(null)
    val exercicios: StateFlow<List<Exercicio>> = _treinoId
        .filterNotNull()
        .flatMapLatest { getExercicios(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val snackbarMessage = MutableStateFlow<String?>(null)

    fun setTreinoId(id: Long) { _treinoId.value = id }

    fun deletar(exercicio: Exercicio) {
        viewModelScope.launch {
            try {
                deleteExercicio(exercicio)
                snackbarMessage.value = "Exercício excluído"
            } catch (e: Exception) {
                snackbarMessage.value = "Erro: ${e.message}"
            }
        }
    }

    fun reordenar(lista: List<Exercicio>) {
        viewModelScope.launch { reordenar.invoke(lista) }
    }
}

// ─── ExercicioFormViewModel ───────────────────────────────────────────────────

data class ExercicioFormState(
    val id: Long = 0,
    val treinoId: Long = 0,
    val nome: String = "",
    val series: String = "4",
    val repeticoes: String = "10",
    val carga: String = "0",
    val descanso: String = "90",
    val observacoes: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null,
    val nomeError: String? = null,
    val seriesError: String? = null,
    val repeticoesError: String? = null,
    val cargaError: String? = null
)

@HiltViewModel
class ExercicioFormViewModel @Inject constructor(
    private val saveExercicio: SaveExercicioUseCase,
    private val getExercicios: GetExerciciosUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExercicioFormState())
    val state: StateFlow<ExercicioFormState> = _state.asStateFlow()

    fun init(treinoId: Long, exercicioId: Long?) {
        _state.value = _state.value.copy(treinoId = treinoId)
        if (exercicioId != null) {
            viewModelScope.launch {
                getExercicios(treinoId).collect { lista ->
                    val ex = lista.firstOrNull { it.id == exercicioId } ?: return@collect
                    _state.value = _state.value.copy(
                        id = ex.id, nome = ex.nome,
                        series = ex.series.toString(),
                        repeticoes = ex.repeticoes.toString(),
                        carga = ex.carga.toString(),
                        descanso = ex.descanso.toString(),
                        observacoes = ex.observacoes
                    )
                }
            }
        }
    }

    fun onNome(v: String) { _state.value = _state.value.copy(nome = v, nomeError = null) }
    fun onSeries(v: String) { _state.value = _state.value.copy(series = v, seriesError = null) }
    fun onRepeticoes(v: String) { _state.value = _state.value.copy(repeticoes = v, repeticoesError = null) }
    fun onCarga(v: String) { _state.value = _state.value.copy(carga = v, cargaError = null) }
    fun onDescanso(v: String) { _state.value = _state.value.copy(descanso = v) }
    fun onObservacoes(v: String) { _state.value = _state.value.copy(observacoes = v) }

    fun salvar() {
        val s = _state.value
        var hasError = false
        if (s.nome.isBlank()) { _state.value = s.copy(nomeError = "Obrigatório"); hasError = true }
        val series = s.series.toIntOrNull()
        if (series == null || series <= 0) { _state.value = _state.value.copy(seriesError = "Inválido"); hasError = true }
        val reps = s.repeticoes.toIntOrNull()
        if (reps == null || reps <= 0) { _state.value = _state.value.copy(repeticoesError = "Inválido"); hasError = true }
        val carga = s.carga.toDoubleOrNull()
        if (carga == null || carga < 0) { _state.value = _state.value.copy(cargaError = "Inválido"); hasError = true }
        if (hasError) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = saveExercicio(
                Exercicio(
                    id = s.id, treinoId = s.treinoId, nome = s.nome,
                    series = series!!, repeticoes = reps!!, carga = carga!!,
                    descanso = s.descanso.toIntOrNull() ?: 90,
                    observacoes = s.observacoes
                )
            )
            result.fold(
                onSuccess = { _state.value = _state.value.copy(isSaved = true, isLoading = false) },
                onFailure = { _state.value = _state.value.copy(error = it.message, isLoading = false) }
            )
        }
    }
}

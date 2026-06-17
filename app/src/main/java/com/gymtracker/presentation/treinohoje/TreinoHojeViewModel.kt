package com.gymtracker.presentation.treinohoje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymtracker.domain.model.*
import com.gymtracker.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class ExercicioComStatus(
    val exercicio: Exercicio,
    val concluido: Boolean = false,
    val cargaUsada: Double = 0.0,
    val seriesFeitas: Int = 0,
    val repeticoesFeitas: Int = 0
)

sealed class TreinoHojeUiState {
    object Loading : TreinoHojeUiState()
    object SemTreino : TreinoHojeUiState()
    data class Ativo(
        val treino: Treino,
        val exercicios: List<ExercicioComStatus>,
        val treinoConcluido: Boolean = false
    ) : TreinoHojeUiState()
    data class Error(val message: String) : TreinoHojeUiState()
}

@HiltViewModel
class TreinoHojeViewModel @Inject constructor(
    private val getTreinoHoje: GetTreinoHojeUseCase,
    private val salvarTreino: SalvarTreinoRealizadoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<TreinoHojeUiState>(TreinoHojeUiState.Loading)
    val uiState: StateFlow<TreinoHojeUiState> = _uiState.asStateFlow()

    init { carregarTreino() }

    fun carregarTreino() {
        viewModelScope.launch {
            try {
                val treino = getTreinoHoje()
                if (treino == null) {
                    _uiState.value = TreinoHojeUiState.SemTreino
                } else {
                    _uiState.value = TreinoHojeUiState.Ativo(
                        treino = treino,
                        exercicios = treino.exercicios.map {
                            ExercicioComStatus(exercicio = it, cargaUsada = it.carga,
                                seriesFeitas = it.series, repeticoesFeitas = it.repeticoes)
                        }
                    )
                }
            } catch (e: Exception) {
                _uiState.value = TreinoHojeUiState.Error(e.message ?: "Erro")
            }
        }
    }

    fun toggleExercicio(exercicioId: Long) {
        val state = _uiState.value as? TreinoHojeUiState.Ativo ?: return
        _uiState.value = state.copy(
            exercicios = state.exercicios.map {
                if (it.exercicio.id == exercicioId) it.copy(concluido = !it.concluido) else it
            }
        )
    }

    fun atualizarCarga(exercicioId: Long, carga: Double) {
        val state = _uiState.value as? TreinoHojeUiState.Ativo ?: return
        _uiState.value = state.copy(
            exercicios = state.exercicios.map {
                if (it.exercicio.id == exercicioId) it.copy(cargaUsada = carga) else it
            }
        )
    }

    fun concluirTreino() {
        val state = _uiState.value as? TreinoHojeUiState.Ativo ?: return
        viewModelScope.launch {
            try {
                val now = Calendar.getInstance()
                val data = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now.time)
                val hora = SimpleDateFormat("HH:mm", Locale.getDefault()).format(now.time)

                val historicoExercicios = state.exercicios.map { ex ->
                    HistoricoExercicio(
                        historicoTreinoId = 0,
                        exercicioId = ex.exercicio.id,
                        nomeExercicio = ex.exercicio.nome,
                        cargaUtilizada = ex.cargaUsada,
                        seriesRealizadas = ex.seriesFeitas,
                        repeticoesRealizadas = ex.repeticoesFeitas
                    )
                }

                salvarTreino(
                    HistoricoTreino(
                        treinoId = state.treino.id,
                        nomeTreino = state.treino.nome,
                        dataExecucao = data,
                        horaExecucao = hora,
                        exercicios = historicoExercicios
                    )
                )

                _uiState.value = state.copy(treinoConcluido = true)
            } catch (e: Exception) {
                _uiState.value = TreinoHojeUiState.Error(e.message ?: "Erro ao salvar treino")
            }
        }
    }
}

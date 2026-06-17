package com.gymtracker.domain.usecase

import com.gymtracker.domain.model.*
import com.gymtracker.domain.repository.*
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

// ─── Treino UseCases ──────────────────────────────────────────────────────────

class GetTreinosUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    operator fun invoke(): Flow<List<Treino>> = repository.getAllTreinos()
}

class GetTreinoByIdUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(id: Long): Treino? = repository.getTreinoById(id)
}

class GetTreinoHojeUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(): Treino? {
        val diaSemana = DiaSemana.fromCalendar(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        return repository.getTreinoPorDia(diaSemana.value)
    }
}

class GetProximoTreinoUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(): Treino? {
        val hoje = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val diaSemanaHoje = DiaSemana.fromCalendar(hoje).value
        val treinos = mutableListOf<Treino>()
        repository.getAllTreinos().collect { treinos.addAll(it) }

        // Próximo após hoje, ciclando
        return treinos
            .filter { it.diaSemana != diaSemanaHoje }
            .minByOrNull { (it.diaSemana - diaSemanaHoje + 7) % 7 }
    }
}

class SaveTreinoUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(treino: Treino): Result<Long> = runCatching {
        require(treino.nome.isNotBlank()) { "Nome do treino é obrigatório" }
        require(treino.grupoMuscular.isNotBlank()) { "Grupo muscular é obrigatório" }
        require(treino.diaSemana in 1..7) { "Dia da semana inválido" }
        if (treino.id == 0L) repository.insertTreino(treino)
        else { repository.updateTreino(treino); treino.id }
    }
}

class DeleteTreinoUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteTreino(id)
}

class DuplicarTreinoUseCase @Inject constructor(
    private val repository: TreinoRepository
) {
    suspend operator fun invoke(id: Long): Long = repository.duplicarTreino(id)
}

// ─── Exercício UseCases ───────────────────────────────────────────────────────

class GetExerciciosUseCase @Inject constructor(
    private val repository: ExercicioRepository
) {
    operator fun invoke(treinoId: Long): Flow<List<Exercicio>> =
        repository.getExerciciosByTreino(treinoId)
}

class SaveExercicioUseCase @Inject constructor(
    private val repository: ExercicioRepository
) {
    suspend operator fun invoke(exercicio: Exercicio): Result<Long> = runCatching {
        require(exercicio.nome.isNotBlank()) { "Nome do exercício é obrigatório" }
        require(exercicio.series > 0) { "Séries deve ser maior que zero" }
        require(exercicio.repeticoes > 0) { "Repetições deve ser maior que zero" }
        require(exercicio.carga >= 0) { "Carga não pode ser negativa" }
        require(exercicio.descanso >= 0) { "Descanso não pode ser negativo" }
        if (exercicio.id == 0L) repository.insertExercicio(exercicio)
        else { repository.updateExercicio(exercicio); exercicio.id }
    }
}

class DeleteExercicioUseCase @Inject constructor(
    private val repository: ExercicioRepository
) {
    suspend operator fun invoke(exercicio: Exercicio) = repository.deleteExercicio(exercicio)
}

class ReordenarExerciciosUseCase @Inject constructor(
    private val repository: ExercicioRepository
) {
    suspend operator fun invoke(exercicios: List<Exercicio>) =
        repository.reordenarExercicios(exercicios)
}

// ─── Histórico UseCases ───────────────────────────────────────────────────────

class GetHistoricoUseCase @Inject constructor(
    private val repository: HistoricoRepository
) {
    operator fun invoke(): Flow<List<HistoricoTreino>> = repository.getAllHistorico()
    operator fun invoke(inicio: String, fim: String): Flow<List<HistoricoTreino>> =
        repository.getHistoricoByPeriodo(inicio, fim)
}

class SalvarTreinoRealizadoUseCase @Inject constructor(
    private val repository: HistoricoRepository
) {
    suspend operator fun invoke(historico: HistoricoTreino): Long =
        repository.salvarTreinoRealizado(historico)
}

class GetEvolucaoUseCase @Inject constructor(
    private val repository: HistoricoRepository
) {
    operator fun invoke(exercicioId: Long): Flow<List<HistoricoExercicio>> =
        repository.getEvolucaoPorExercicio(exercicioId)

    operator fun invoke(nomeExercicio: String, byName: Boolean = true): Flow<List<HistoricoExercicio>> =
        repository.getEvolucaoPorNomeExercicio(nomeExercicio)
}

class GetDashboardInfoUseCase @Inject constructor(
    private val treinoRepository: TreinoRepository,
    private val historicoRepository: HistoricoRepository
) {
    suspend operator fun invoke(): DashboardInfo {
        val cal = Calendar.getInstance()
        val diaSemana = DiaSemana.fromCalendar(cal.get(Calendar.DAY_OF_WEEK))
        val treinoHoje = treinoRepository.getTreinoPorDia(diaSemana.value)

        // Próximo treino
        var proximoTreino: Treino? = null
        treinoRepository.getAllTreinos().collect { treinos ->
            proximoTreino = treinos
                .filter { it.diaSemana != diaSemana.value }
                .minByOrNull { (it.diaSemana - diaSemana.value + 7) % 7 }
        }

        // Contagem do mês
        val anoMes = "%04d-%02d".format(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
        var treinosNoMes = 0
        historicoRepository.countTreinosNoMes(anoMes).collect { treinosNoMes = it }

        val ultimoTreino = historicoRepository.getUltimoTreino()

        return DashboardInfo(
            dataAtual = "%02d/%02d/%04d".format(
                cal.get(Calendar.DAY_OF_MONTH),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.YEAR)
            ),
            horaAtual = "%02d:%02d".format(
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE)
            ),
            diaSemanaAtual = diaSemana.label,
            treinoHoje = treinoHoje,
            proximoTreino = proximoTreino,
            treinosNoMes = treinosNoMes,
            frequenciaSemanal = calcularFrequencia(treinosNoMes),
            ultimoTreino = ultimoTreino?.dataExecucao?.let { formatarData(it) }
        )
    }

    private fun calcularFrequencia(treinosNoMes: Int): String {
        val semanas = 4.3
        val freq = treinosNoMes / semanas
        return "${String.format("%.0f", freq)}x por semana"
    }

    private fun formatarData(data: String): String {
        // "yyyy-MM-dd" → "dd/MM/yyyy"
        val parts = data.split("-")
        return if (parts.size == 3) "${parts[2]}/${parts[1]}/${parts[0]}" else data
    }
}

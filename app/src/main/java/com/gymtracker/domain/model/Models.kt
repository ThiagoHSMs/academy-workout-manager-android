package com.gymtracker.domain.model

// ─── Treino ───────────────────────────────────────────────────────────────────

data class Treino(
    val id: Long = 0,
    val nome: String,
    val diaSemana: Int,
    val grupoMuscular: String,
    val observacoes: String = "",
    val exercicios: List<Exercicio> = emptyList()
) {
    val diaSemanaLabel: String get() = DiaSemana.fromInt(diaSemana).label
}

enum class DiaSemana(val value: Int, val label: String) {
    DOMINGO(1, "Domingo"),
    SEGUNDA(2, "Segunda-feira"),
    TERCA(3, "Terça-feira"),
    QUARTA(4, "Quarta-feira"),
    QUINTA(5, "Quinta-feira"),
    SEXTA(6, "Sexta-feira"),
    SABADO(7, "Sábado");

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.value == value } ?: SEGUNDA
        fun fromCalendar(calendarDayOfWeek: Int) = when (calendarDayOfWeek) {
            java.util.Calendar.SUNDAY -> DOMINGO
            java.util.Calendar.MONDAY -> SEGUNDA
            java.util.Calendar.TUESDAY -> TERCA
            java.util.Calendar.WEDNESDAY -> QUARTA
            java.util.Calendar.THURSDAY -> QUINTA
            java.util.Calendar.FRIDAY -> SEXTA
            java.util.Calendar.SATURDAY -> SABADO
            else -> SEGUNDA
        }
    }
}

// ─── Exercício ─────────────────────────────────────────────────────────────────

data class Exercicio(
    val id: Long = 0,
    val treinoId: Long,
    val nome: String,
    val series: Int,
    val repeticoes: Int,
    val carga: Double,
    val descanso: Int,
    val observacoes: String = "",
    val ordem: Int = 0
)

// ─── Histórico ─────────────────────────────────────────────────────────────────

data class HistoricoTreino(
    val id: Long = 0,
    val treinoId: Long?,
    val nomeTreino: String,
    val dataExecucao: String,
    val horaExecucao: String,
    val exercicios: List<HistoricoExercicio> = emptyList()
)

data class HistoricoExercicio(
    val id: Long = 0,
    val historicoTreinoId: Long,
    val exercicioId: Long?,
    val nomeExercicio: String,
    val cargaUtilizada: Double,
    val seriesRealizadas: Int,
    val repeticoesRealizadas: Int
)

// ─── Dashboard ─────────────────────────────────────────────────────────────────

data class DashboardInfo(
    val dataAtual: String,
    val horaAtual: String,
    val diaSemanaAtual: String,
    val treinoHoje: Treino?,
    val proximoTreino: Treino?,
    val treinosNoMes: Int,
    val frequenciaSemanal: String,
    val ultimoTreino: String?
)

// ─── Evolução ──────────────────────────────────────────────────────────────────

data class PontoEvolucao(
    val data: String,
    val carga: Double,
    val nomeExercicio: String
)

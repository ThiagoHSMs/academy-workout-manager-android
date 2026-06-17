package com.gymtracker.data.mapper

import com.gymtracker.data.local.entity.*
import com.gymtracker.domain.model.*

// ─── Treino ───────────────────────────────────────────────────────────────────

fun TreinoEntity.toDomain(exercicios: List<Exercicio> = emptyList()) = Treino(
    id = id,
    nome = nome,
    diaSemana = diaSemana,
    grupoMuscular = grupoMuscular,
    observacoes = observacoes,
    exercicios = exercicios
)

fun Treino.toEntity() = TreinoEntity(
    id = id,
    nome = nome,
    diaSemana = diaSemana,
    grupoMuscular = grupoMuscular,
    observacoes = observacoes
)

// ─── Exercício ─────────────────────────────────────────────────────────────────

fun ExercicioEntity.toDomain() = Exercicio(
    id = id,
    treinoId = treinoId,
    nome = nome,
    series = series,
    repeticoes = repeticoes,
    carga = carga,
    descanso = descanso,
    observacoes = observacoes,
    ordem = ordem
)

fun Exercicio.toEntity() = ExercicioEntity(
    id = id,
    treinoId = treinoId,
    nome = nome,
    series = series,
    repeticoes = repeticoes,
    carga = carga,
    descanso = descanso,
    observacoes = observacoes,
    ordem = ordem
)

// ─── Histórico ─────────────────────────────────────────────────────────────────

fun HistoricoTreinoEntity.toDomain(exercicios: List<HistoricoExercicio> = emptyList()) = HistoricoTreino(
    id = id,
    treinoId = treinoId,
    nomeTreino = nomeTreino,
    dataExecucao = dataExecucao,
    horaExecucao = horaExecucao,
    exercicios = exercicios
)

fun HistoricoTreino.toEntity() = HistoricoTreinoEntity(
    id = id,
    treinoId = treinoId,
    nomeTreino = nomeTreino,
    dataExecucao = dataExecucao,
    horaExecucao = horaExecucao
)

fun HistoricoExercicioEntity.toDomain() = HistoricoExercicio(
    id = id,
    historicoTreinoId = historicoTreinoId,
    exercicioId = exercicioId,
    nomeExercicio = nomeExercicio,
    cargaUtilizada = cargaUtilizada,
    seriesRealizadas = seriesRealizadas,
    repeticoesRealizadas = repeticoesRealizadas
)

fun HistoricoExercicio.toEntity() = HistoricoExercicioEntity(
    id = id,
    historicoTreinoId = historicoTreinoId,
    exercicioId = exercicioId,
    nomeExercicio = nomeExercicio,
    cargaUtilizada = cargaUtilizada,
    seriesRealizadas = seriesRealizadas,
    repeticoesRealizadas = repeticoesRealizadas
)

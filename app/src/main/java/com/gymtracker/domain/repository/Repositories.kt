package com.gymtracker.domain.repository

import com.gymtracker.domain.model.*
import kotlinx.coroutines.flow.Flow

interface TreinoRepository {
    fun getAllTreinos(): Flow<List<Treino>>
    suspend fun getTreinoById(id: Long): Treino?
    suspend fun getTreinoPorDia(diaSemana: Int): Treino?
    fun searchTreinos(query: String): Flow<List<Treino>>
    suspend fun insertTreino(treino: Treino): Long
    suspend fun updateTreino(treino: Treino)
    suspend fun deleteTreino(id: Long)
    suspend fun duplicarTreino(id: Long): Long
}

interface ExercicioRepository {
    fun getExerciciosByTreino(treinoId: Long): Flow<List<Exercicio>>
    suspend fun getExercicioById(id: Long): Exercicio?
    fun searchExercicios(query: String): Flow<List<Exercicio>>
    suspend fun insertExercicio(exercicio: Exercicio): Long
    suspend fun updateExercicio(exercicio: Exercicio)
    suspend fun deleteExercicio(exercicio: Exercicio)
    suspend fun reordenarExercicios(exercicios: List<Exercicio>)
}

interface HistoricoRepository {
    fun getAllHistorico(): Flow<List<HistoricoTreino>>
    fun getHistoricoByPeriodo(inicio: String, fim: String): Flow<List<HistoricoTreino>>
    fun getHistoricoByTreino(treinoId: Long): Flow<List<HistoricoTreino>>
    fun countTreinosNoMes(anoMes: String): Flow<Int>
    fun getDatasDoMes(anoMes: String): Flow<List<String>>
    suspend fun getUltimoTreino(): HistoricoTreino?
    suspend fun salvarTreinoRealizado(historico: HistoricoTreino): Long
    suspend fun deleteHistorico(id: Long)
    fun getEvolucaoPorExercicio(exercicioId: Long): Flow<List<HistoricoExercicio>>
    fun getEvolucaoPorNomeExercicio(nome: String): Flow<List<HistoricoExercicio>>
    fun searchHistoricoByExercicio(query: String): Flow<List<HistoricoTreino>>
}

package com.gymtracker.data.local.dao

import androidx.room.*
import com.gymtracker.data.local.entity.*
import kotlinx.coroutines.flow.Flow

// ─── TreinoDao ────────────────────────────────────────────────────────────────

@Dao
interface TreinoDao {

    @Query("SELECT * FROM treinos ORDER BY diaSemana ASC")
    fun getAllTreinos(): Flow<List<TreinoEntity>>

    @Query("SELECT * FROM treinos WHERE id = :id")
    suspend fun getTreinoById(id: Long): TreinoEntity?

    @Query("SELECT * FROM treinos WHERE diaSemana = :diaSemana LIMIT 1")
    suspend fun getTreinoPorDia(diaSemana: Int): TreinoEntity?

    @Query("SELECT * FROM treinos WHERE nome LIKE '%' || :query || '%' OR grupoMuscular LIKE '%' || :query || '%'")
    fun searchTreinos(query: String): Flow<List<TreinoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTreino(treino: TreinoEntity): Long

    @Update
    suspend fun updateTreino(treino: TreinoEntity)

    @Delete
    suspend fun deleteTreino(treino: TreinoEntity)

    @Query("DELETE FROM treinos WHERE id = :id")
    suspend fun deleteTreinoById(id: Long)
}

// ─── ExercicioDao ─────────────────────────────────────────────────────────────

@Dao
interface ExercicioDao {

    @Query("SELECT * FROM exercicios WHERE treinoId = :treinoId ORDER BY ordem ASC")
    fun getExerciciosByTreino(treinoId: Long): Flow<List<ExercicioEntity>>

    @Query("SELECT * FROM exercicios WHERE id = :id")
    suspend fun getExercicioById(id: Long): ExercicioEntity?

    @Query("SELECT * FROM exercicios WHERE nome LIKE '%' || :query || '%'")
    fun searchExercicios(query: String): Flow<List<ExercicioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercicio(exercicio: ExercicioEntity): Long

    @Update
    suspend fun updateExercicio(exercicio: ExercicioEntity)

    @Delete
    suspend fun deleteExercicio(exercicio: ExercicioEntity)

    @Query("UPDATE exercicios SET ordem = :ordem WHERE id = :id")
    suspend fun updateOrdem(id: Long, ordem: Int)

    @Query("SELECT * FROM exercicios WHERE treinoId = :treinoId ORDER BY ordem ASC")
    suspend fun getExerciciosByTreinoSync(treinoId: Long): List<ExercicioEntity>
}

// ─── HistoricoDao ─────────────────────────────────────────────────────────────

@Dao
interface HistoricoDao {

    @Query("SELECT * FROM historico_treinos ORDER BY dataExecucao DESC, horaExecucao DESC")
    fun getAllHistorico(): Flow<List<HistoricoTreinoEntity>>

    @Query("""
        SELECT * FROM historico_treinos 
        WHERE dataExecucao BETWEEN :inicio AND :fim 
        ORDER BY dataExecucao DESC
    """)
    fun getHistoricoByPeriodo(inicio: String, fim: String): Flow<List<HistoricoTreinoEntity>>

    @Query("SELECT * FROM historico_treinos WHERE treinoId = :treinoId ORDER BY dataExecucao DESC")
    fun getHistoricoByTreino(treinoId: Long): Flow<List<HistoricoTreinoEntity>>

    @Query("SELECT * FROM historico_treinos WHERE id = :id")
    suspend fun getHistoricoById(id: Long): HistoricoTreinoEntity?

    @Query("""
        SELECT COUNT(*) FROM historico_treinos 
        WHERE dataExecucao LIKE :anoMes || '%'
    """)
    fun countTreinosNoMes(anoMes: String): Flow<Int>

    @Query("""
        SELECT dataExecucao FROM historico_treinos 
        WHERE dataExecucao LIKE :anoMes || '%'
    """)
    fun getDatasDoMes(anoMes: String): Flow<List<String>>

    @Query("SELECT * FROM historico_treinos ORDER BY dataExecucao DESC LIMIT 1")
    suspend fun getUltimoTreino(): HistoricoTreinoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoricoTreino(historico: HistoricoTreinoEntity): Long

    @Delete
    suspend fun deleteHistoricoTreino(historico: HistoricoTreinoEntity)

    // Exercícios do histórico
    @Query("SELECT * FROM historico_exercicios WHERE historicoTreinoId = :historicoId")
    fun getExerciciosDoHistorico(historicoId: Long): Flow<List<HistoricoExercicioEntity>>

    @Query("SELECT * FROM historico_exercicios WHERE historicoTreinoId = :historicoId")
    suspend fun getExerciciosDoHistoricoSync(historicoId: Long): List<HistoricoExercicioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoricoExercicio(exercicio: HistoricoExercicioEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoricoExercicios(exercicios: List<HistoricoExercicioEntity>)

    // Evolução de carga por exercício
    @Query("""
        SELECT he.* FROM historico_exercicios he
        INNER JOIN historico_treinos ht ON he.historicoTreinoId = ht.id
        WHERE he.exercicioId = :exercicioId
        ORDER BY ht.dataExecucao ASC
    """)
    fun getEvolucaoPorExercicio(exercicioId: Long): Flow<List<HistoricoExercicioEntity>>

    @Query("""
        SELECT he.*, ht.dataExecucao FROM historico_exercicios he
        INNER JOIN historico_treinos ht ON he.historicoTreinoId = ht.id
        WHERE he.nomeExercicio LIKE '%' || :nomeExercicio || '%'
        ORDER BY ht.dataExecucao ASC
    """)
    fun getEvolucaoPorNomeExercicio(nomeExercicio: String): Flow<List<HistoricoExercicioEntity>>

    @Query("""
        SELECT ht.* FROM historico_treinos ht
        INNER JOIN historico_exercicios he ON he.historicoTreinoId = ht.id
        WHERE he.nomeExercicio LIKE '%' || :query || '%'
        ORDER BY ht.dataExecucao DESC
    """)
    fun searchHistoricoByExercicio(query: String): Flow<List<HistoricoTreinoEntity>>
}

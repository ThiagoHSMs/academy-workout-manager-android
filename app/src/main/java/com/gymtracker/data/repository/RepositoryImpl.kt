package com.gymtracker.data.repository

import com.gymtracker.data.local.dao.ExercicioDao
import com.gymtracker.data.local.dao.HistoricoDao
import com.gymtracker.data.local.dao.TreinoDao
import com.gymtracker.data.mapper.*
import com.gymtracker.domain.model.*
import com.gymtracker.domain.repository.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// ─── TreinoRepositoryImpl ─────────────────────────────────────────────────────

@Singleton
class TreinoRepositoryImpl @Inject constructor(
    private val treinoDao: TreinoDao,
    private val exercicioDao: ExercicioDao
) : TreinoRepository {

    override fun getAllTreinos(): Flow<List<Treino>> =
        treinoDao.getAllTreinos().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun getTreinoById(id: Long): Treino? {
        val entity = treinoDao.getTreinoById(id) ?: return null
        val exercicios = exercicioDao.getExerciciosByTreinoSync(id).map { it.toDomain() }
        return entity.toDomain(exercicios)
    }

    override suspend fun getTreinoPorDia(diaSemana: Int): Treino? {
        val entity = treinoDao.getTreinoPorDia(diaSemana) ?: return null
        val exercicios = exercicioDao.getExerciciosByTreinoSync(entity.id).map { it.toDomain() }
        return entity.toDomain(exercicios)
    }

    override fun searchTreinos(query: String): Flow<List<Treino>> =
        treinoDao.searchTreinos(query).map { list -> list.map { it.toDomain() } }

    override suspend fun insertTreino(treino: Treino): Long =
        treinoDao.insertTreino(treino.toEntity())

    override suspend fun updateTreino(treino: Treino) =
        treinoDao.updateTreino(treino.toEntity())

    override suspend fun deleteTreino(id: Long) =
        treinoDao.deleteTreinoById(id)

    override suspend fun duplicarTreino(id: Long): Long {
        val original = getTreinoById(id) ?: return -1
        val novoId = insertTreino(
            original.copy(id = 0, nome = "${original.nome} (cópia)", diaSemana = 0)
        )
        original.exercicios.forEachIndexed { index, ex ->
            exercicioDao.insertExercicio(ex.copy(id = 0, treinoId = novoId, ordem = index).toEntity())
        }
        return novoId
    }
}

// ─── ExercicioRepositoryImpl ──────────────────────────────────────────────────

@Singleton
class ExercicioRepositoryImpl @Inject constructor(
    private val exercicioDao: ExercicioDao
) : ExercicioRepository {

    override fun getExerciciosByTreino(treinoId: Long): Flow<List<Exercicio>> =
        exercicioDao.getExerciciosByTreino(treinoId).map { list -> list.map { it.toDomain() } }

    override suspend fun getExercicioById(id: Long): Exercicio? =
        exercicioDao.getExercicioById(id)?.toDomain()

    override fun searchExercicios(query: String): Flow<List<Exercicio>> =
        exercicioDao.searchExercicios(query).map { list -> list.map { it.toDomain() } }

    override suspend fun insertExercicio(exercicio: Exercicio): Long =
        exercicioDao.insertExercicio(exercicio.toEntity())

    override suspend fun updateExercicio(exercicio: Exercicio) =
        exercicioDao.updateExercicio(exercicio.toEntity())

    override suspend fun deleteExercicio(exercicio: Exercicio) =
        exercicioDao.deleteExercicio(exercicio.toEntity())

    override suspend fun reordenarExercicios(exercicios: List<Exercicio>) {
        exercicios.forEachIndexed { index, ex ->
            exercicioDao.updateOrdem(ex.id, index)
        }
    }
}

// ─── HistoricoRepositoryImpl ──────────────────────────────────────────────────

@Singleton
class HistoricoRepositoryImpl @Inject constructor(
    private val historicoDao: HistoricoDao
) : HistoricoRepository {

    override fun getAllHistorico(): Flow<List<HistoricoTreino>> =
        historicoDao.getAllHistorico().map { list -> list.map { it.toDomain() } }

    override fun getHistoricoByPeriodo(inicio: String, fim: String): Flow<List<HistoricoTreino>> =
        historicoDao.getHistoricoByPeriodo(inicio, fim).map { list -> list.map { it.toDomain() } }

    override fun getHistoricoByTreino(treinoId: Long): Flow<List<HistoricoTreino>> =
        historicoDao.getHistoricoByTreino(treinoId).map { list -> list.map { it.toDomain() } }

    override fun countTreinosNoMes(anoMes: String): Flow<Int> =
        historicoDao.countTreinosNoMes(anoMes)

    override fun getDatasDoMes(anoMes: String): Flow<List<String>> =
        historicoDao.getDatasDoMes(anoMes)

    override suspend fun getUltimoTreino(): HistoricoTreino? =
        historicoDao.getUltimoTreino()?.toDomain()

    override suspend fun salvarTreinoRealizado(historico: HistoricoTreino): Long {
        val historicoId = historicoDao.insertHistoricoTreino(historico.toEntity())
        val exerciciosEntities = historico.exercicios.map {
            it.copy(historicoTreinoId = historicoId).toEntity()
        }
        historicoDao.insertHistoricoExercicios(exerciciosEntities)
        return historicoId
    }

    override suspend fun deleteHistorico(id: Long) {
        val entity = historicoDao.getHistoricoById(id) ?: return
        historicoDao.deleteHistoricoTreino(entity)
    }

    override fun getEvolucaoPorExercicio(exercicioId: Long): Flow<List<HistoricoExercicio>> =
        historicoDao.getEvolucaoPorExercicio(exercicioId).map { list -> list.map { it.toDomain() } }

    override fun getEvolucaoPorNomeExercicio(nome: String): Flow<List<HistoricoExercicio>> =
        historicoDao.getEvolucaoPorNomeExercicio(nome).map { list -> list.map { it.toDomain() } }

    override fun searchHistoricoByExercicio(query: String): Flow<List<HistoricoTreino>> =
        historicoDao.searchHistoricoByExercicio(query).map { list -> list.map { it.toDomain() } }
}

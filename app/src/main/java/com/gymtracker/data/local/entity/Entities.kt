package com.gymtracker.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// ─── Treino ──────────────────────────────────────────────────────────────────

@Entity(tableName = "treinos")
data class TreinoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val diaSemana: Int,          // 1=Dom, 2=Seg, 3=Ter, 4=Qua, 5=Qui, 6=Sex, 7=Sáb
    val grupoMuscular: String,
    val observacoes: String = ""
)

// ─── Exercício ────────────────────────────────────────────────────────────────

@Entity(
    tableName = "exercicios",
    foreignKeys = [ForeignKey(
        entity = TreinoEntity::class,
        parentColumns = ["id"],
        childColumns = ["treinoId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("treinoId")]
)
data class ExercicioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val treinoId: Long,
    val nome: String,
    val series: Int,
    val repeticoes: Int,
    val carga: Double,
    val descanso: Int,           // segundos
    val observacoes: String = "",
    val ordem: Int = 0
)

// ─── Histórico Treino ─────────────────────────────────────────────────────────

@Entity(
    tableName = "historico_treinos",
    foreignKeys = [ForeignKey(
        entity = TreinoEntity::class,
        parentColumns = ["id"],
        childColumns = ["treinoId"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index("treinoId")]
)
data class HistoricoTreinoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val treinoId: Long?,
    val nomeTreino: String,      // snapshot do nome
    val dataExecucao: String,    // "yyyy-MM-dd"
    val horaExecucao: String     // "HH:mm"
)

// ─── Histórico Exercício ──────────────────────────────────────────────────────

@Entity(
    tableName = "historico_exercicios",
    foreignKeys = [
        ForeignKey(
            entity = HistoricoTreinoEntity::class,
            parentColumns = ["id"],
            childColumns = ["historicoTreinoId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ExercicioEntity::class,
            parentColumns = ["id"],
            childColumns = ["exercicioId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("historicoTreinoId"), Index("exercicioId")]
)
data class HistoricoExercicioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val historicoTreinoId: Long,
    val exercicioId: Long?,
    val nomeExercicio: String,   // snapshot do nome
    val cargaUtilizada: Double,
    val seriesRealizadas: Int,
    val repeticoesRealizadas: Int
)

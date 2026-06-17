package com.gymtracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gymtracker.data.local.dao.ExercicioDao
import com.gymtracker.data.local.dao.HistoricoDao
import com.gymtracker.data.local.dao.TreinoDao
import com.gymtracker.data.local.entity.*

@Database(
    entities = [
        TreinoEntity::class,
        ExercicioEntity::class,
        HistoricoTreinoEntity::class,
        HistoricoExercicioEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {
    abstract fun treinoDao(): TreinoDao
    abstract fun exercicioDao(): ExercicioDao
    abstract fun historicoDao(): HistoricoDao

    companion object {
        const val DATABASE_NAME = "gym_tracker.db"
    }
}

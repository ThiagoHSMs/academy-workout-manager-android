package com.gymtracker.di

import android.content.Context
import androidx.room.Room
import com.gymtracker.data.local.dao.ExercicioDao
import com.gymtracker.data.local.dao.HistoricoDao
import com.gymtracker.data.local.dao.TreinoDao
import com.gymtracker.data.local.database.GymDatabase
import com.gymtracker.data.repository.ExercicioRepositoryImpl
import com.gymtracker.data.repository.HistoricoRepositoryImpl
import com.gymtracker.data.repository.TreinoRepositoryImpl
import com.gymtracker.domain.repository.ExercicioRepository
import com.gymtracker.domain.repository.HistoricoRepository
import com.gymtracker.domain.repository.TreinoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GymDatabase =
        Room.databaseBuilder(
            context,
            GymDatabase::class.java,
            GymDatabase.DATABASE_NAME
        ).build()

    @Provides
    fun provideTreinoDao(db: GymDatabase): TreinoDao = db.treinoDao()

    @Provides
    fun provideExercicioDao(db: GymDatabase): ExercicioDao = db.exercicioDao()

    @Provides
    fun provideHistoricoDao(db: GymDatabase): HistoricoDao = db.historicoDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTreinoRepository(impl: TreinoRepositoryImpl): TreinoRepository

    @Binds
    @Singleton
    abstract fun bindExercicioRepository(impl: ExercicioRepositoryImpl): ExercicioRepository

    @Binds
    @Singleton
    abstract fun bindHistoricoRepository(impl: HistoricoRepositoryImpl): HistoricoRepository
}

package com.gymtracker

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GymTrackerApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)

            val treinoChannel = NotificationChannel(
                CHANNEL_TREINO_ID,
                "Lembretes de Treino",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notificações sobre seu treino do dia"
            }

            val cronometroChannel = NotificationChannel(
                CHANNEL_CRONOMETRO_ID,
                "Cronômetro de Descanso",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Alertas de fim de descanso"
            }

            manager.createNotificationChannel(treinoChannel)
            manager.createNotificationChannel(cronometroChannel)
        }
    }

    companion object {
        const val CHANNEL_TREINO_ID = "treino_channel"
        const val CHANNEL_CRONOMETRO_ID = "cronometro_channel"
    }
}

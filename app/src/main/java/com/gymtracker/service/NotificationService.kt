package com.gymtracker.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.gymtracker.GymTrackerApp
import com.gymtracker.MainActivity
import com.gymtracker.domain.model.DiaSemana
import com.gymtracker.domain.repository.TreinoRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Calendar
import java.util.concurrent.TimeUnit

// ─── Worker ───────────────────────────────────────────────────────────────────

@HiltWorker
class TreinoNotificationWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val treinoRepository: TreinoRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val diaSemana = DiaSemana.fromCalendar(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
            val treino = treinoRepository.getTreinoPorDia(diaSemana.value)

            val titulo = "Bom dia, campeão! 💪"
            val mensagem = if (treino != null) {
                "Hoje é ${diaSemana.label}. Seu treino: ${treino.nome}"
            } else {
                "Hoje é ${diaSemana.label}. Dia de descanso - aproveite!"
            }

            mostrarNotificacao(applicationContext, titulo, mensagem)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun mostrarNotificacao(context: Context, titulo: String, mensagem: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, GymTrackerApp.CHANNEL_TREINO_ID)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle(titulo)
            .setContentText(mensagem)
            .setStyle(NotificationCompat.BigTextStyle().bigText(mensagem))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(1001, notification)
    }
}

// ─── Agendador ────────────────────────────────────────────────────────────────

object TreinoNotificationScheduler {

    fun agendarNotificacaoDiaria(context: Context) {
        // Calcular delay até 7h da manhã
        val agora = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 7)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            if (before(agora)) add(Calendar.DAY_OF_MONTH, 1)
        }
        val delayMs = target.timeInMillis - agora.timeInMillis

        val request = PeriodicWorkRequestBuilder<TreinoNotificationWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .setConstraints(Constraints.Builder().build())
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "treino_notification",
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }

    fun cancelar(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork("treino_notification")
    }
}

// ─── BootReceiver ─────────────────────────────────────────────────────────────

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            TreinoNotificationScheduler.agendarNotificacaoDiaria(context)
        }
    }
}

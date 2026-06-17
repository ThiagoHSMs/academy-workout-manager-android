package com.gymtracker.presentation.components

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CronometroCard() {
    var tempoTotal by remember { mutableStateOf(60) }
    var tempoRestante by remember { mutableStateOf(60) }
    var ativo by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(ativo, tempoTotal) {
        if (ativo) {
            while (tempoRestante > 0 && ativo) {
                delay(1000)
                tempoRestante--
            }
            if (tempoRestante == 0) {
                ativo = false
                vibrar(context)
            }
        }
    }

    val progresso = tempoRestante.toFloat() / tempoTotal.toFloat()
    val cor = when {
        progresso > 0.5f -> MaterialTheme.colorScheme.secondary
        progresso > 0.25f -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Cronômetro de Descanso",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progresso },
                    modifier = Modifier.size(120.dp),
                    color = cor,
                    strokeWidth = 6.dp,
                    trackColor = MaterialTheme.colorScheme.outline
                )
                Text(
                    "%02d:%02d".format(tempoRestante / 60, tempoRestante % 60),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = cor
                )
            }

            Spacer(Modifier.height(12.dp))

            // Presets
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(30, 60, 90, 120).forEach { segundos ->
                    FilterChip(
                        selected = tempoTotal == segundos,
                        onClick = {
                            tempoTotal = segundos
                            tempoRestante = segundos
                            ativo = false
                        },
                        label = { Text("${segundos}s", style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = { ativo = !ativo },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        if (ativo) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (ativo) "Pausar" else "Iniciar",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(
                    onClick = { tempoRestante = tempoTotal; ativo = false },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Default.Replay, "Reiniciar",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

fun vibrar(context: Context) {
    try {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            val manager = context.getSystemService(VibratorManager::class.java)
            manager.defaultVibrator.vibrate(
                VibrationEffect.createWaveform(longArrayOf(0, 500, 200, 500), -1)
            )
        } else {
            @Suppress("DEPRECATION")
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            @Suppress("DEPRECATION")
            vibrator.vibrate(longArrayOf(0, 500, 200, 500), -1)
        }
    } catch (e: Exception) {
        // Silencioso se vibração não disponível
    }
}

package com.gymtracker.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Paleta fitness premium (dark)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00E5FF),          // Cyan neon
    onPrimary = Color(0xFF003544),
    primaryContainer = Color(0xFF004D61),
    onPrimaryContainer = Color(0xFFB3F0FF),
    secondary = Color(0xFF00FF88),         // Verde neon
    onSecondary = Color(0xFF003822),
    secondaryContainer = Color(0xFF005233),
    onSecondaryContainer = Color(0xFFB3FFD8),
    tertiary = Color(0xFFFF6B35),          // Laranja accent
    onTertiary = Color(0xFF4A1500),
    tertiaryContainer = Color(0xFF6B2200),
    onTertiaryContainer = Color(0xFFFFDBCE),
    error = Color(0xFFFF5252),
    onError = Color(0xFF690005),
    background = Color(0xFF0A0E1A),        // Azul bem escuro
    onBackground = Color(0xFFE8EAED),
    surface = Color(0xFF111827),           // Card escuro
    onSurface = Color(0xFFE8EAED),
    surfaceVariant = Color(0xFF1E2D3D),
    onSurfaceVariant = Color(0xFFBCC7D5),
    outline = Color(0xFF2D3F52),
    surfaceTint = Color(0xFF00E5FF)
)

@Composable
fun GymTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}

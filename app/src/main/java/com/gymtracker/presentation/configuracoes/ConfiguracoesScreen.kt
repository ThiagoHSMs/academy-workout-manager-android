package com.gymtracker.presentation.configuracoes

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gymtracker.data.local.database.GymDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

// ─── ViewModel ────────────────────────────────────────────────────────────────

@HiltViewModel
class ConfiguracoesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _mensagem = MutableStateFlow<String?>(null)
    val mensagem: StateFlow<String?> = _mensagem.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun exportarBackup(onArquivoPronto: (Intent) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                withContext(Dispatchers.IO) {
                    val dbFile = context.getDatabasePath(GymDatabase.DATABASE_NAME)
                    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                    val backupFile = File(context.cacheDir, "GymTracker_Backup_$timestamp.db")
                    dbFile.copyTo(backupFile, overwrite = true)

                    val uri = FileProvider.getUriForFile(
                        context, "${context.packageName}.fileprovider", backupFile
                    )

                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "application/octet-stream"
                        putExtra(Intent.EXTRA_STREAM, uri)
                        putExtra(Intent.EXTRA_SUBJECT, "GymTracker Backup")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }

                    withContext(Dispatchers.Main) {
                        onArquivoPronto(Intent.createChooser(intent, "Compartilhar backup"))
                    }
                }
                _mensagem.value = "Backup exportado com sucesso!"
            } catch (e: Exception) {
                _mensagem.value = "Erro ao exportar: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun limparMensagem() { _mensagem.value = null }
}

// ─── Screen ───────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracoesScreen(
    navController: NavController,
    viewModel: ConfiguracoesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val mensagem by viewModel.mensagem.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(mensagem) {
        mensagem?.let { snackbarHostState.showSnackbar(it); viewModel.limparMensagem() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configurações", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Seção Backup
            SecaoTitulo("Backup e Restauração")

            ConfigCard(
                icon = Icons.Default.CloudUpload,
                titulo = "Exportar Backup",
                descricao = "Salve uma cópia do banco de dados",
                onClick = {
                    viewModel.exportarBackup { intent ->
                        context.startActivity(intent)
                    }
                },
                isLoading = isLoading
            )

            ConfigCard(
                icon = Icons.Default.CloudDownload,
                titulo = "Importar Backup",
                descricao = "Restaurar dados de um arquivo de backup",
                onClick = { /* TODO: implementar picker de arquivo */ }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            // Seção Notificações
            SecaoTitulo("Notificações")

            ConfigCard(
                icon = Icons.Default.Notifications,
                titulo = "Lembrete de Treino",
                descricao = "Receba uma notificação diária com seu treino do dia",
                onClick = { }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            // Seção Sobre
            SecaoTitulo("Sobre")

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoRow("Versão", "1.0.0")
                    InfoRow("Banco de dados", "Room SQLite")
                    InfoRow("Framework", "Jetpack Compose")
                    InfoRow("Arquitetura", "MVVM + Clean Architecture")
                }
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
fun SecaoTitulo(titulo: String) {
    Text(
        titulo,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ConfigCard(
    icon: ImageVector,
    titulo: String,
    descricao: String,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(titulo, fontWeight = FontWeight.SemiBold)
                Text(descricao, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            if (isLoading) {
                CircularProgressIndicator(Modifier.size(20.dp), color = MaterialTheme.colorScheme.primary)
            } else {
                Icon(Icons.Default.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun InfoRow(label: String, valor: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(valor, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
    }
}

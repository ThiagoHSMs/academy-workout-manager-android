package com.gymtracker.presentation.treinohoje

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gymtracker.domain.repository.HistoricoRepository
import com.gymtracker.presentation.components.CronometroCard
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

// ─── Mini Calendário ViewModel ────────────────────────────────────────────────

@HiltViewModel
class MiniCalendarioViewModel @Inject constructor(
    private val historicoRepository: HistoricoRepository
) : ViewModel() {

    private val cal = Calendar.getInstance()
    val anoMes = "%04d-%02d".format(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1)
    val hoje = "%04d-%02d-%02d".format(
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH)
    )

    val datasComTreino: StateFlow<Set<String>> = historicoRepository
        .getDatasDoMes(anoMes)
        .map { it.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())
}

// ─── TreinoHojeScreen ─────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreinoHojeScreen(
    navController: NavController,
    viewModel: TreinoHojeViewModel = hiltViewModel(),
    calendarioViewModel: MiniCalendarioViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val datasComTreino by calendarioViewModel.datasComTreino.collectAsState()

    var showCronometro by remember { mutableStateOf(false) }
    var showCalendario by remember { mutableStateOf(false) }
    var showConcluidoDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Treino de Hoje", fontWeight = FontWeight.Bold) },
                actions = {
                    // Botão Calendário
                    IconButton(onClick = { showCalendario = !showCalendario }) {
                        Icon(
                            if (showCalendario) Icons.Default.CalendarMonth else Icons.Default.CalendarToday,
                            contentDescription = "Calendário",
                            tint = if (showCalendario) MaterialTheme.colorScheme.secondary
                                   else MaterialTheme.colorScheme.primary
                        )
                    }
                    // Botão Cronômetro
                    IconButton(onClick = { showCronometro = !showCronometro }) {
                        Icon(
                            Icons.Default.Timer,
                            contentDescription = "Cronômetro",
                            tint = if (showCronometro) MaterialTheme.colorScheme.secondary
                                   else MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        when (val state = uiState) {
            is TreinoHojeUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            is TreinoHojeUiState.SemTreino -> {
                LazyColumn(
                    Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Cronômetro e Calendário lado a lado
                    if (showCronometro || showCalendario) {
                        item {
                            PainelSuperior(
                                showCronometro = showCronometro,
                                showCalendario = showCalendario,
                                datasComTreino = datasComTreino,
                                hoje = calendarioViewModel.hoje,
                                anoMes = calendarioViewModel.anoMes
                            )
                        }
                    }
                    item {
                        Box(
                            Modifier.fillParentMaxHeight(0.6f).fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.SelfImprovement, null,
                                    modifier = Modifier.size(80.dp),
                                    tint = MaterialTheme.colorScheme.secondary)
                                Spacer(Modifier.height(16.dp))
                                Text("Dia de Descanso",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold)
                                Text("Nenhum treino programado para hoje",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            is TreinoHojeUiState.Ativo -> {
                LazyColumn(
                    Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Cronômetro e Calendário lado a lado
                    if (showCronometro || showCalendario) {
                        item {
                            PainelSuperior(
                                showCronometro = showCronometro,
                                showCalendario = showCalendario,
                                datasComTreino = datasComTreino,
                                hoje = calendarioViewModel.hoje,
                                anoMes = calendarioViewModel.anoMes
                            )
                        }
                    }

                    // Header Treino
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(state.treino.nome,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer)
                                Text(state.treino.grupoMuscular,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f))
                                if (state.treino.observacoes.isNotBlank()) {
                                    Text(state.treino.observacoes,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f))
                                }
                            }
                        }
                    }

                    // Exercícios
                    items(state.exercicios, key = { it.exercicio.id }) { exStatus ->
                        ExercicioHojeCard(
                            exercicioStatus = exStatus,
                            onToggle = { viewModel.toggleExercicio(exStatus.exercicio.id) },
                            onCargaChange = { viewModel.atualizarCarga(exStatus.exercicio.id, it) }
                        )
                    }

                    // Botão Concluir
                    if (!state.treinoConcluido) {
                        item {
                            val todosFeitos = state.exercicios.all { it.concluido }
                            Button(
                                onClick = { showConcluidoDialog = true },
                                modifier = Modifier.fillMaxWidth().height(56.dp),
                                enabled = state.exercicios.isNotEmpty(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (todosFeitos) MaterialTheme.colorScheme.secondary
                                    else MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Icon(Icons.Default.CheckCircle, null)
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    if (todosFeitos) "Concluir Treino ✓" else "Encerrar Treino",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                )
                            ) {
                                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.EmojiEvents, null,
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(32.dp))
                                    Spacer(Modifier.width(12.dp))
                                    Text("Treino concluído! Ótimo trabalho!",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer)
                                }
                            }
                        }
                    }

                    item { Spacer(Modifier.height(80.dp)) }
                }
            }

            is TreinoHojeUiState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(state.message, color = MaterialTheme.colorScheme.error)
                }
            }
        }

        if (showConcluidoDialog) {
            AlertDialog(
                onDismissRequest = { showConcluidoDialog = false },
                icon = { Icon(Icons.Default.FitnessCenter, null, tint = MaterialTheme.colorScheme.primary) },
                title = { Text("Encerrar Treino?") },
                text = { Text("O treino será salvo no histórico com os dados atuais.") },
                confirmButton = {
                    Button(onClick = {
                        viewModel.concluirTreino()
                        showConcluidoDialog = false
                    }) { Text("Confirmar") }
                },
                dismissButton = {
                    TextButton(onClick = { showConcluidoDialog = false }) { Text("Cancelar") }
                }
            )
        }
    }
}

// ─── Painel Superior: Cronômetro + Calendário lado a lado ─────────────────────

@Composable
fun PainelSuperior(
    showCronometro: Boolean,
    showCalendario: Boolean,
    datasComTreino: Set<String>,
    hoje: String,
    anoMes: String
) {
    // Ambos visíveis: Row com os dois cards lado a lado
    // Só um visível: ocupa largura total
    if (showCronometro && showCalendario) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(Modifier.weight(1f)) { CronometroCard() }
            Box(Modifier.weight(1f)) { MiniCalendarioCard(datasComTreino, hoje, anoMes) }
        }
    } else if (showCronometro) {
        CronometroCard()
    } else if (showCalendario) {
        MiniCalendarioCard(datasComTreino, hoje, anoMes)
    }
}

// ─── Mini Calendário Card ─────────────────────────────────────────────────────

val diasSemanaAbrev = listOf("D", "S", "T", "Q", "Q", "S", "S")
val mesesAbrev = listOf("Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez")

@Composable
fun MiniCalendarioCard(
    datasComTreino: Set<String>,
    hoje: String,
    anoMes: String
) {
    val parts = anoMes.split("-")
    val ano = parts[0].toInt()
    val mes = parts[1].toInt()

    val cal = Calendar.getInstance()
    cal.set(ano, mes - 1, 1)
    val primeiroDia = cal.get(Calendar.DAY_OF_WEEK) - 1  // 0 = Dom
    val diasNoMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    val treinosNoMes = datasComTreino.count { it.startsWith(anoMes) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(10.dp)) {

            // Cabeçalho mês
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "${mesesAbrev[mes - 1]} $ano",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        "$treinosNoMes treinos",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(6.dp))

            // Cabeçalho dias da semana
            Row(Modifier.fillMaxWidth()) {
                diasSemanaAbrev.forEach { d ->
                    Text(
                        d,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // Grid de dias
            val totalCells = primeiroDia + diasNoMes
            val linhas = (totalCells + 6) / 7

            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(linhas) { linha ->
                    Row(Modifier.fillMaxWidth()) {
                        repeat(7) { col ->
                            val celula = linha * 7 + col
                            val dia = celula - primeiroDia + 1
                            val dataStr = "%04d-%02d-%02d".format(ano, mes, dia)
                            val temTreino = datasComTreino.contains(dataStr)
                            val eHoje = dataStr == hoje

                            Box(
                                modifier = Modifier.weight(1f).aspectRatio(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                if (dia in 1..diasNoMes) {
                                    val bgColor = when {
                                        eHoje -> MaterialTheme.colorScheme.primary
                                        temTreino -> MaterialTheme.colorScheme.secondary
                                        else -> Color.Transparent
                                    }
                                    val textColor = when {
                                        eHoje -> MaterialTheme.colorScheme.onPrimary
                                        temTreino -> MaterialTheme.colorScheme.onSecondary
                                        else -> MaterialTheme.colorScheme.onSurface
                                    }

                                    Box(
                                        Modifier
                                            .fillMaxSize(0.85f)
                                            .clip(CircleShape)
                                            .background(bgColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "$dia",
                                            color = textColor,
                                            fontSize = 9.sp,
                                            fontWeight = if (eHoje || temTreino) FontWeight.Bold else FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            // Legenda compacta
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                LegendaMini(MaterialTheme.colorScheme.primary, "Hoje")
                LegendaMini(MaterialTheme.colorScheme.secondary, "Treino")
            }
        }
    }
}

@Composable
fun LegendaMini(cor: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(cor))
        Spacer(Modifier.width(3.dp))
        Text(label, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// ─── ExercicioHojeCard ────────────────────────────────────────────────────────

@Composable
fun ExercicioHojeCard(
    exercicioStatus: ExercicioComStatus,
    onToggle: () -> Unit,
    onCargaChange: (Double) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }
    var cargaText by remember { mutableStateOf(exercicioStatus.cargaUsada.toString()) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (exercicioStatus.concluido)
                MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = exercicioStatus.concluido,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(Modifier.width(8.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        exercicioStatus.exercicio.nome,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "${exercicioStatus.exercicio.series}x${exercicioStatus.exercicio.repeticoes}  •  ${exercicioStatus.cargaUsada} kg  •  ${exercicioStatus.exercicio.descanso}s",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expandido = !expandido }) {
                    Icon(
                        if (expandido) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expandido) {
                Column(Modifier.padding(top = 8.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cargaText,
                        onValueChange = { text ->
                            cargaText = text
                            text.toDoubleOrNull()?.let { onCargaChange(it) }
                        },
                        label = { Text("Carga utilizada (kg)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    if (exercicioStatus.exercicio.observacoes.isNotBlank()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            exercicioStatus.exercicio.observacoes,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
